package com.pd.springboot.service;

/**
 * redis适配器
 * 
 * @author thinkpad
 *
 */
public interface IPdSwitchService {
    boolean getLookupSwtich(String code);
    boolean getRedisSwtich(String code);
    void refreshLookupSwitch();
}
