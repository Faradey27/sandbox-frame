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
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import org.kaaproject.avro.ui.gwt.client.util.BusyAsyncCallback;
import org.kaaproject.kaa.sandbox.web.client.Sandbox;
import org.kaaproject.kaa.sandbox.web.client.mvp.ClientFactory;
import org.kaaproject.kaa.sandbox.web.client.mvp.place.LogsPlace;
import org.kaaproject.kaa.sandbox.web.client.mvp.view.LogsView;
import org.kaaproject.kaa.sandbox.web.client.util.Analytics;
import org.kaaproject.kaa.sandbox.web.client.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class LogsActivity extends AbstractActivity {

    private static final String LOGS_SERVLET_URL = GWT.getModuleBaseURL() + "servlet/logsServlet";

    private final LogsPlace place;
    private final ClientFactory clientFactory;
    private LogsView view;

    private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

    public LogsActivity(LogsPlace place, ClientFactory clientFactory) {
        this.place = place;
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        Analytics.switchScreen(Analytics.GET_LOGS_ACTION);
        view = clientFactory.getLogsView();
        bind(eventBus);
        containerWidget.setWidget(view.asWidget());
    }

    @Override
    public void onStop() {
        for (HandlerRegistration registration : registrations) {
            registration.removeHandler();
        }
        registrations.clear();
    }

    private void bind(final EventBus eventBus) {
        view.reset();

        registrations.add(view.getBackButton().addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        clientFactory.getPlaceController().goTo(place.getPreviousPlace());
                    }
                }));

        fillView();
    }

    private void fillView() {
        Sandbox.getSandboxService().getLogsEnabled(new BusyAsyncCallback<Boolean>() {
            @Override
            public void onFailureImpl(Throwable throwable) {
                String message = Utils.getErrorMessage(throwable);
                view.setErrorMessage(message);
                Analytics.sendException(message);
            }

            @Override
            public void onSuccessImpl(Boolean enabled) {
                view.setGetLogsEnabled(enabled);
                if (enabled) {
                    registrations.add(view.getGetLogsButton().addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent clickEvent) {
                            getLogs();
                        }
                    }));
                }

            }
        });
    }

    private void getLogs() {
        Analytics.sendEvent(Analytics.GET_LOGS_ACTION, "getting logs");
        Sandbox.redirectToUrl(LOGS_SERVLET_URL);
    }
}
