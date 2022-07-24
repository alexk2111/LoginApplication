package com.onix.internship.okucherenko.ui.settings

import com.onix.internship.okucherenko.arch.BaseViewModel
import com.onix.internship.okucherenko.data.repository.entity.Settings

class SettingsViewModel: BaseViewModel() {

    var sliderValue: Int = Settings.currentSetting.level
}