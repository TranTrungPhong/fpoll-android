package com.framgia.fpoll.ui.pollmanage.information;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by Nhahv0902 on 2/24/2017.
 * <></>
 */
public interface PollInformationContract {
    interface View extends BaseView {
        void startUiVoting();
    }

    interface Presenter {
        void clickLinkVote();
        void clickViewOption();
        void clickViewSetting();
    }
}