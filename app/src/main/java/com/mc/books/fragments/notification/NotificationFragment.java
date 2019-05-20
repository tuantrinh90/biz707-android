package com.mc.books.fragments.notification;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.bon.sharepreferences.AppPreferences;
import com.mc.adapter.NotificationAdapter;
import com.mc.adapter.NotificationSystemAdapter;
import com.mc.books.R;
import com.mc.books.fragments.home.infomationBook.InformationBookFragment;
import com.mc.books.fragments.home.listLesson.ListLessonFragment;
import com.mc.books.fragments.home.listSubject.ListSubjectFragment;
import com.mc.books.fragments.notification.detailnotification.DetailNotificationFragment;
import com.mc.common.fragments.BaseMvpFragment;
import com.mc.customizes.customTab.CustomTab;
import com.mc.customizes.recyclerview.ExtRecyclerView;
import com.mc.models.notification.Notification;
import com.mc.models.notification.NotificationSystem;
import com.mc.models.notification.UnReadNotification;
import com.mc.utilities.Constant;
import com.mc.utilities.FragmentUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.mc.utilities.Constant.KEY_COMMON_BOOK_NOTI;
import static com.mc.utilities.Constant.KEY_COURSES_BOOK_NOTI;
import static com.mc.utilities.Constant.KEY_EXERCISES_BOOK_NOTI;
import static com.mc.utilities.Constant.KEY_TYPE_NOTI_MANUAL;

public class NotificationFragment extends BaseMvpFragment<INotificationView, INotificationPresenter<INotificationView>> implements INotificationView {
    public static NotificationFragment newInstance() {
        Bundle args = new Bundle();
        NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.rvMyNoti)
    ExtRecyclerView rvMyNoti;
    @BindView(R.id.rvSystemNoti)
    ExtRecyclerView rvSystemNoti;
    @BindView(R.id.tabYourNews)
    CustomTab tabYourNews;
    @BindView(R.id.tabSystemNews)
    CustomTab tabSystemNews;

    private static final int TAB_YOUR_NEWS = 0;
    private static final int TAB_SYSTEM_NEWS = 1;
    private int defaulttab = TAB_YOUR_NEWS;


    @Override
    public INotificationPresenter<INotificationView> createPresenter() {
        return new NotificationPresenter<>(getAppComponent());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindButterKnife(view);
        initView();
        loadDataMyNoti();
        loadDataSystemNoti();
        if (!AppPreferences.getInstance(mActivity).getBoolean(Constant.KEY_BACK_NOTIFICATION)) {
            changeTab(TAB_YOUR_NEWS);
        } else {
            changeTab(TAB_SYSTEM_NEWS);
        }

        mMainActivity.onShowBottomBar(true);
    }

    private void loadDataMyNoti() {
        presenter.getListNotification(rvMyNoti.getItemCount());
    }

    private void loadDataSystemNoti() {
        presenter.getListNotificationSystem(rvSystemNoti.getItemCount());
    }

    private void initView() {
        rvMyNoti.setAdapter(new NotificationAdapter(getContext(), null, this::directNotification))
                .setLoadMoreListener(this::loadDataMyNoti)
                .setRefreshListener(this::loadDataMyNoti)
                .build();

        rvSystemNoti.setAdapter(new NotificationSystemAdapter(getContext(), null, this::directDetailNotiSystem))
                .setLoadMoreListener(this::loadDataSystemNoti)
                .setRefreshListener(this::loadDataSystemNoti)
                .build();
    }

    private void changeTab(int index) {
        tabYourNews.setActiveMode(false);
        tabSystemNews.setActiveMode(false);
        defaulttab = index;

        switch (index) {
            case TAB_YOUR_NEWS:
                tabYourNews.setActiveMode(true);
                rvMyNoti.setVisibility(View.VISIBLE);
                rvSystemNoti.setVisibility(View.GONE);
                break;
            case TAB_SYSTEM_NEWS:
                rvMyNoti.setVisibility(View.GONE);
                rvSystemNoti.setVisibility(View.VISIBLE);
                tabSystemNews.setActiveMode(true);
                break;
            default:
                break;
        }
    }

    private void directNotification(Notification notification) {
        presenter.getUnReadNoti(notification.getId());
        if (notification.getType().equals(Constant.KEY_TYPE_NOTI_MYBOOK)) {
            int bookId = notification.getDetailMsg().getId();
            switch (notification.getAction()) {
                case KEY_COMMON_BOOK_NOTI:
                    mMainActivity.changeTabBottom(Constant.HOME_TAB);
                    FragmentUtils.replaceFragment(getActivity(), InformationBookFragment.newInstance(bookId), fragment -> mMainActivity.fragments.add(fragment));
                    break;

                case KEY_COURSES_BOOK_NOTI:
                    mMainActivity.changeTabBottom(Constant.HOME_TAB);
                    FragmentUtils.replaceFragment(getActivity(), ListLessonFragment.newInstance(bookId, 0), fragment -> mMainActivity.fragments.add(fragment));
                    break;

                case KEY_EXERCISES_BOOK_NOTI:
                    mMainActivity.changeTabBottom(Constant.HOME_TAB);
                    FragmentUtils.replaceFragment(getActivity(), ListSubjectFragment.newInstance(bookId), fragment -> mMainActivity.fragments.add(fragment));
                    break;
            }

        } else if (notification.getType().equals(KEY_TYPE_NOTI_MANUAL)) {
            int id = notification.getDataId();
            FragmentUtils.replaceFragment(getActivity(), DetailNotificationFragment.newInstance(id), fragment -> mMainActivity.fragments.add(fragment));
        }
    }

    private void directDetailNotiSystem(NotificationSystem notificationSystem) {
        int id = notificationSystem.getId();
        FragmentUtils.replaceFragment(getActivity(), DetailNotificationFragment.newInstance(id), fragment -> mMainActivity.fragments.add(fragment));
    }

    @OnClick({R.id.tabYourNews, R.id.tabSystemNews})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tabYourNews:
                changeTab(TAB_YOUR_NEWS);
                AppPreferences.getInstance(mActivity).putBoolean(Constant.KEY_BACK_NOTIFICATION, false);
                break;
            case R.id.tabSystemNews:
                changeTab(TAB_SYSTEM_NEWS);
                AppPreferences.getInstance(mActivity).putBoolean(Constant.KEY_BACK_NOTIFICATION, true);
                break;
            default:
                break;
        }
    }

    @Override
    public int getTitleId() {
        return R.string.notification;
    }

    @Override
    public int getResourceId() {
        return R.layout.notification_fragment;
    }

    @Override
    public void onShowLoading(boolean isShow) {
        showProgress(isShow);
    }

    @Override
    public void showNotification(List<Notification> notifications) {
        rvMyNoti.addItems(notifications);
    }

    @Override
    public void showUnReadNoti(UnReadNotification unReadNotification) {
        Log.e("tuan", String.valueOf(unReadNotification.getIsRead()));
    }

    @Override
    public void showNotificationSystem(List<NotificationSystem> notificationsSys) {
        rvSystemNoti.addItems(notificationsSys);
    }
}
