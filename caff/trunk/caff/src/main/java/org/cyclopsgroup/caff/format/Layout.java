package org.cyclopsgroup.caff.format;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public interface Layout<T>
{
    void print(T object, Writer output) throws IOException;

    void populate(T object, Reader reader) throws IOException;
}
