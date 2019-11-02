package com.example.masterdetailsmvvm;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.example.masterdetailsmvvm.model.MostViewed;
import com.example.masterdetailsmvvm.viewmodel.MostViewedViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    private Observer<MostViewed.Results> observer;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_clickReturnCorrectObject() {
        List<MostViewed.Results> mostViewedList = generateMostViewedList();
        MostViewedViewModel viewModel = new MostViewedViewModel();
        viewModel.init();

        viewModel.getSelected().observeForever(observer);
        // When
        viewModel.getMostViewed().setValue(mostViewedList);
        viewModel.onItemClick(0);
        // then
        verify(observer).onChanged(mostViewedList.get(0));
    }

    private List<MostViewed.Results> generateMostViewedList() {
        List<MostViewed.Results> mMostViewedList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MostViewed.Results mMostViewed = new MostViewed.Results();
            mMostViewed.setTitle("Epstein’s Autopsy ‘Points to Homicide,’ Pathologist Hired by Brother Claims");
            mMostViewed.setAbstractX("The New York City medical examiner strongly disputed the claim that evidence from the autopsy suggested strangulation.");
            mMostViewed.setByline("By AZI PAYBARAH");
            mMostViewed.setPublished_date("2019-10-30");

            mMostViewedList.add(mMostViewed);
        }
        return mMostViewedList;
    }

}