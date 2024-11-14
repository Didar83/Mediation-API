package kz.mediation.api.mediation_executor.util;

import kz.mediation.api.greenapi.model_examples.notification.DeviceInfo;
import kz.mediation.api.greenapi.model_examples.notification.StateInstanceChanged;

public class DefaultWebhookHandlerImpl extends WebhookHandlerAbstract {

    @Override
    public void processStateInstanceChanged(StateInstanceChanged stateInstanceChanged) {
        super.processStateInstanceChanged(stateInstanceChanged);
    }

    @Override
    public void processDeviceInfo(DeviceInfo deviceInfo) {
        super.processDeviceInfo(deviceInfo);
    }
}
