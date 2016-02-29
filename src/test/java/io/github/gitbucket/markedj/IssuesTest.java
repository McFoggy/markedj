package io.github.gitbucket.markedj;

import static io.github.gitbucket.markedj.Resources.loadResourceAsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class IssuesTest {
    @Test
    public void issue8_list_is_rendered_correctly_without_separation_blank_line() throws Exception {
        Options o = new Options();
        o.setGfm(false);
        o.setTables(false);
        
        String withBlankLineInput = loadResourceAsString("issues/issue-8-blank-line.md");
        String withoutBlankLineInput = loadResourceAsString("issues/issue-8-no-line.md");
        String withBlankLineResult = Marked.marked(withBlankLineInput, o);
        String withoutBlankLineResult = Marked.marked(withoutBlankLineInput, o);
        
        assertThat(withoutBlankLineResult, is(withBlankLineResult));
    }
}
