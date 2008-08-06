package org.cyclopsgroup.jcli.jline;

enum ArgumentsInspectorState
{
    /**
     * Ready for new option of argument
     */
    READY,
    /**
     * Writing short option name
     */
    OPTION,
    /**
     * Writing long option name
     */
    LONG_OPTION,
    /**
     * Writing option value
     */
    OPTION_VALUE,
    /**
     * Writing argument
     */
    ARGUMENT;
}
