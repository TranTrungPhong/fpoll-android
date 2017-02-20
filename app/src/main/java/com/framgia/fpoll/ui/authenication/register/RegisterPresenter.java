package com.framgia.fpoll.ui.authenication.register;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.User;
import com.framgia.fpoll.util.UserValidation;

/**
 * Created by tuanbg on 2/17/17.
 */
public class RegisterPresenter implements RegisterContract.Presenter {
    private final String TAG = this.getClass().getSimpleName();
    private RegisterContract.View mView;
    private User mUser;

    public RegisterPresenter(RegisterContract.View view, User user) {
        mView = view;
        mUser = user;
    }

    @Override
    public void registerUser() {
        new UserValidation(mUser).validate(new UserValidation.CallBack() {
            @Override
            public void onError(UserValidation.Error error) {
                switch (error) {
                    case USER_NAME:
                        mView.showMessageError(R.string.msg_username_not_empty);
                        break;
                    case EMAIL:
                        mView.showMessageError(R.string.msg_email_invalidate);
                        break;
                    case PASSWORD:
                        mView.showMessageError(R.string.msg_password_not_empty);
                        break;
                    case CONFIRM_PASSWORD:
                        mView.showMessageError(R.string.msg_confirm_pass_not_success);
                        break;
                    case PASSWORD_LENGTH:
                        mView.showMessageError(R.string.msg_length_password);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onValidateSuccess() {
                // TODO: 19/02/2017 Register
            }
        });
    }

    @Override
    public void openGallery() {
        mView.chooseImage();
    }

    @Override
    public void setUserUrlImage(String url) {
        mUser.setAvatar(url);
    }

    public User getUser() {
        return mUser;
    }
}