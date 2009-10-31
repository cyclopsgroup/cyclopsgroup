package org.cyclopsgroup.eulerer.p205;


/**
 * Adapter that referencing {@link DiceGame}
 *
 * @author <a href="mailto:jiaqi@cyclopsgroup.org">Jiaqi Guo</a>
 */
public class Solution
    implements Runnable
{
    private final DiceGame game = new DiceGame();

    /**
     * @inheritDoc
     */
    @Override
    public void run()
    {
        game.comparePeterAndColin();
    }
}
