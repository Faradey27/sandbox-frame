/*
 * Copyright 2014-2015 CyberVision, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kaaproject.kaa.sandbox.web.client.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.PopupPanel;
import org.kaaproject.avro.ui.gwt.client.util.BusyAsyncCallback;
import org.kaaproject.kaa.sandbox.web.client.Sandbox;
import org.kaaproject.kaa.sandbox.web.client.mvp.ClientFactory;
import org.kaaproject.kaa.sandbox.web.client.mvp.place.ChangeKaaHostPlace;
import org.kaaproject.kaa.sandbox.web.client.mvp.place.LogsPlace;
import org.kaaproject.kaa.sandbox.web.client.mvp.view.HeaderView;
import org.kaaproject.kaa.sandbox.web.client.mvp.view.widget.ActionsLabel;
import org.kaaproject.kaa.sandbox.web.client.mvp.view.widget.ActionsLabel.ActionMenuItemListener;
import org.kaaproject.kaa.sandbox.web.client.util.Analytics;
import org.kaaproject.kaa.sandbox.web.client.util.Utils;

public class HeaderActivity extends AbstractActivity {

    private Place place;
    private final ClientFactory clientFactory;
    private final HeaderView headerView;
    
    private int kaaNodeWebPort;

    private ActionsLabel subMenuItem;

    public HeaderActivity(Place place, ClientFactory clientFactory) {
        this.place = place;
        this.clientFactory = clientFactory;
        this.headerView = clientFactory.getHeaderView();
    }
    
    public void setPlace(Place place) {
    	this.place = place;
    }

    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        bind(headerView, eventBus);
        containerWidget.setWidget(headerView.asWidget());
    }

    @Override
    public void onStop() {
        
    }

    private void bind(final HeaderView headerView, final EventBus eventBus) {
        
        headerView.getHeaderMenuItems().reset();
        
        headerView.getHeaderMenuItems().addMenuItem(Utils.constants.kaaAdminWeb(), new ActionMenuItemListener() {
            @Override
            public void onMenuItemSelected() {
                gotoKaaAdminWeb();
            }
        });

        headerView.getHeaderMenuItems().addMenuItem(Utils.constants.avroUiSandboxWeb(), new ActionMenuItemListener() {
            @Override
            public void onMenuItemSelected() {
                gotoAvroUiSandboxWeb();
            }
        });

        subMenuItem = headerView.getHeaderMenuItems().addMenuItem(Utils.constants.settings(), true,
                new ActionMenuItemListener() {
            @Override
            public void onMenuItemSelected() {
                onSubmenuClick();
            }
        });

        Sandbox.getSandboxService().changeKaaHostEnabled(new BusyAsyncCallback<Boolean>() {
            @Override
            public void onFailureImpl(Throwable caught) {
            }

            @Override
            public void onSuccessImpl(Boolean enabled) {
                if (enabled) {
                    subMenuItem.addMenuItem(Utils.constants.changeKaaHost(), new ActionMenuItemListener() {
                        @Override
                        public void onMenuItemSelected() {
                            clientFactory.getPlaceController().goTo(new ChangeKaaHostPlace(place));
                        }
                    });
                }
            }
        });
        
        Sandbox.getSandboxService().getLogsEnabled(new BusyAsyncCallback<Boolean>() {
            @Override
            public void onFailureImpl(Throwable caught) {
            }

            @Override
            public void onSuccessImpl(Boolean enabled) {
                if (enabled) {
                    subMenuItem.addMenuItem(Utils.constants.getSandboxLogs(), new ActionMenuItemListener() {
                        @Override
                        public void onMenuItemSelected() {
                            clientFactory.getPlaceController().goTo(new LogsPlace(place));
                        }
                    });
                }
            }
        });

        Sandbox.getSandboxService().getKaaNodeWebPort(new BusyAsyncCallback<Integer>() {

            @Override
            public void onFailureImpl(Throwable caught) {
            }

            @Override
            public void onSuccessImpl(Integer result) {
                if (result != null) {
                    kaaNodeWebPort = result.intValue();
                }
            }
        });
    }

    /*TODO: try to avoid this...*/
    private void onSubmenuClick() {
        final PopupPanel popup = subMenuItem.getActionsPopup();
        if (!popup.isShowing() && !subMenuItem.isVisible()) {

            final Element parent = headerView.getHeaderMenuItems().getElement();
            popup.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
                public void setPosition(int offsetWidth, int offsetHeight) {
                    int left = parent.getAbsoluteLeft();
                    int top = parent.getAbsoluteTop() + parent.getOffsetHeight() + 2;
                    if (left + popup.getOffsetWidth() > Window .getClientWidth()) {
                        left = parent.getAbsoluteRight() - popup.getOffsetWidth();
                    }
                    popup.setPopupPosition(left, top);
                }
            });
            popup.show();
        }
    }

    private void gotoKaaAdminWeb() {
        Analytics.sendEvent(Analytics.GOTO_ADMIN_UI_ACTION);
        String url = "http://" + Sandbox.getWindowHost() + ":" + kaaNodeWebPort + "/kaaAdmin";
        Sandbox.redirectToUrl(url);
    }

    private void gotoAvroUiSandboxWeb() {
        Analytics.sendEvent(Analytics.GOTO_AVRO_UI_SANDBOX_ACTION);
        Sandbox.redirectToModule("avroUiSandbox");
    }

}
