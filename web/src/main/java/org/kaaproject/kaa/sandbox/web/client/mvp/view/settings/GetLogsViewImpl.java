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

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.kaaproject.kaa.sandbox.web.client.mvp.view.GetLogsView;
import org.kaaproject.kaa.sandbox.web.client.mvp.view.base.BaseViewImpl;
import org.kaaproject.kaa.sandbox.web.client.util.Utils;

public class GetLogsViewImpl extends BaseViewImpl implements GetLogsView {

    private VerticalPanel getLogsPanel;
    private Button getLogsButton;

    public GetLogsViewImpl() {
        super(true);
        setBackEnabled(true);
    }

    @Override
    protected String getViewTitle() {
        return "Sandbox logs";
    }

    @Override
    protected void initCenterPanel() {

        getLogsPanel = new VerticalPanel();
        HTML getLogsLabel = new HTML("Here you can download sandbox logs!");
        getLogsLabel.addStyleName(Utils.sandboxStyle.descriptionLabel());
        getLogsPanel.add(getLogsLabel);

        getLogsButton = new Button("Get logs");
        getLogsPanel.add(getLogsButton);

        detailsPanel.add(getLogsPanel);
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
