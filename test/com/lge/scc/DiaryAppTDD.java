package com.lge.scc;

import com.lge.scc.FitnessDiaryActivity;
import com.lge.scc.R;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class DiaryAppTDD {

    @Test
    public void shouldHaveHappySmiles() throws Exception {
        String hello = new FitnessDiaryActivity().getResources().getString(R.string.hello);
        assertThat(hello, equalTo("FitnessDiaryMain"));
    }
}
