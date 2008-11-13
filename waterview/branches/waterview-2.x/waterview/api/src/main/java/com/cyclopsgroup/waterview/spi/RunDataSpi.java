package com.cyclopsgroup.waterview.spi;

import java.util.List;

import com.cyclopsgroup.waterview.DynamicLink;
import com.cyclopsgroup.waterview.RunData;

/**
 * Implementation side RunData interface
 *
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo</a>
 */
public interface RunDataSpi
    extends RunData
{
    public class InvalidInput
    {
        private String errorMessage;

        private String inputName;

        public InvalidInput( String inputName, String errorMessage )
        {
            this.inputName = inputName;
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage()
        {
            return errorMessage;
        }

        public String getInputName()
        {
            return inputName;
        }
    }

    Throwable getError();

    String getErrorMessage();

    List<InvalidInput> getInvalidInputs();

    List<String> getMessages();

    void setApplicationBaseUrl( String applicationBaseUrl );

    void setError( Throwable e );

    void setErrorMessage( String errorMessage );

    void setLink( DynamicLink link );

    void setRequestPath( String requestPath );
}
