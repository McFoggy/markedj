package io.github.gitbucket.markedj;

import static io.github.gitbucket.markedj.Resources.loadResourceAsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class MarkedJSTest {
    MarkedJS marker;
    
    @Before
    public void init() {
        marker = MarkedJS.getInstance();
        
    }

    @Test
    public void marker_was_initialized() {
        assertThat(marker, notNullValue());
    }

    @Test
    public void can_parse_a_string() throws Exception {
        String withBlankLineInput = loadResourceAsString("issues/issue-8-blank-line.md");
        String withoutBlankLineInput = loadResourceAsString("issues/issue-8-no-line.md");
        String withBlankLineResult = marker.parse(withBlankLineInput);
        String withoutBlankLineResult = marker.parse(withoutBlankLineInput);
        
        assertThat(withoutBlankLineResult, is(withBlankLineResult));

    }
}
