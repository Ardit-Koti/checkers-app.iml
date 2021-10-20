package com.webcheckers.util;
import spark.*;
import org.junit.jupiter.api.*;

import javax.sound.sampled.EnumControl;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import java.util.logging.Logger;

/**
 * Author: Ardit Koti
 */
@Tag("util-tier")
public class MessageTest {
    private Message CuT;

    @BeforeEach
    void setup()
    {
        CuT = mock(Message.class);
    }

    @Test
    void errorTest()
    {
        Message m = Message.error(CuT.getText());
        Assertions.assertSame(Message.Type.ERROR, m.getType());
    }

    @Test
    void infoTest()
    {
        Message m = Message.info(CuT.getText());
        Assertions.assertSame(Message.Type.INFO, m.getType());
    }

    @Test
    void toStringTest()
    {
        String s_rep = CuT.toString();
        Assertions.assertSame("{Msg " + CuT.getType() + " '" + CuT.getText() + "'}" , s_rep );
    }
}
