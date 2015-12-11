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

package org.kaaproject.kaa.sandbox.web.client.mvp.view.settings;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.kaaproject.kaa.sandbox.web.client.mvp.view.LogsView;
import org.kaaproject.kaa.sandbox.web.client.mvp.view.base.BaseViewImpl;
import org.kaaproject.kaa.sandbox.web.client.util.Utils;

public class LogsViewImpl extends BaseViewImpl implements LogsView {

    private VerticalPanel getLogsPanel;
    private Button getLogsButton;

    public LogsViewImpl() {
        super(true);
        setBackEnabled(true);
    }

    @Override
    protected String getViewTitle() {
        return Utils.constants.sandboxLogs();
    }

    @Override
    protected void initCenterPanel() {

        getLogsPanel = new VerticalPanel();
        getLogsPanel.getElement().getStyle().setPaddingTop(20, Style.Unit.PX);
        HTML getLogsLabel = new HTML(Utils.messages.getLogsMessage());
        getLogsLabel.addStyleName(Utils.sandboxStyle.descriptionLabel());
        getLogsLabel.getElement().getStyle().setPaddingBottom(20, Style.Unit.PX);
        getLogsPanel.add(getLogsLabel);

        getLogsButton = new Button(Utils.constants.getLogs());
        getLogsPanel.add(getLogsButton);

        detailsPanel.add(getLogsPanel);
        headerPanel.setVisible(true);
    }

    @Override
    protected void resetImpl() {
        setGetLogsEnabled(false);
    }

    @Override
    public HasClickHandlers getGetLogsButton() {
        return getLogsButton;
    }

    @Override
    public void setGetLogsEnabled(Boolean enabled) {
        getLogsPanel.setVisible(enabled);
    }

}
