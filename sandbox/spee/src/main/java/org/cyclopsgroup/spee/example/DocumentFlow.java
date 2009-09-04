package org.cyclopsgroup.spee.example;

import java.util.concurrent.TimeUnit;

import org.cyclopsgroup.spee.ExecutionContext;
import org.cyclopsgroup.spee.Flow;
import org.cyclopsgroup.spee.Predicate;

public class DocumentFlow implements Flow<String>
{
    /**
     * @inheritDoc
     */
    public String execute(ExecutionContext exe)
    {
        DocumentManager mgr = exe.getEngineContext().findComponent(DocumentManager.class);

        // Send document to secretary for review and wait for result
        mgr.sendToSecretary(exe.getId());
        String result = exe.waitFor(new Predicate<String>()
        {
            @Override
            public boolean evaluate(String evaluatee)
            {
                return evaluatee.startsWith("ReviewedBySecretary:");
            }
        }, 5, TimeUnit.HOURS);
        if (result == null)
        {
            return "SecretaryReviewTimeout";
        }
        if (!result.equals("ReviewedBySecretary:Approved"))
        {
            return "SecretaryReviewFailed:" + result;
        }

        // Send document to two reviewers for review and wait for both results
        mgr.sendToReviewer(exe.getId(), "John");
        mgr.sendToReviewer(exe.getId(), "Mike");
        result = exe.waitFor(new Predicate<String>()
        {
            @Override
            public boolean evaluate(String evaluatee)
            {
                return evaluatee.startsWith("ReviewedByReviewer:") && evaluatee.endsWith(":Approved");
            }
        }, 10, TimeUnit.MINUTES);
        if (result == null)
        {
            return "ReviewTimeout";
        }

        // After review, publish document
        mgr.publish(exe.getId());
        return "Successful";
    }
}
