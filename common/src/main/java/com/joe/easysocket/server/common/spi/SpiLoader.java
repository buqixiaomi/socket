package com.joe.easysocket.server.common.spi;

import java.util.Properties;

import com.joe.easysocket.server.common.exception.ConfigIllegalException;

import com.joe.utils.reflect.ClassUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 加载SPI
 *
 * @author joe
 * @version 2018.06.27 11:24
 */
@Slf4j
public class SpiLoader {
    /**
     * 加载SPI
     *
     * @param spiClass    spi对应的class
     * @param environment 系统环境
     * @param <T>         SPI实际类型
     * @return spi实例
     */
    public static <T extends Spi> T loadSpi(String spiClass, Properties environment) {
        log.debug("初始化SPI[{}]", spiClass);
        try {
            T t = ClassUtils.getInstance(spiClass);
            t.setProperties(environment);
            log.debug("[{}]初始化完毕", spiClass);
            return t;
        } catch (Throwable e) {
            log.error("构造实例[{}]失败，可能是没有无参数构造器，请为类[{}]增加无参数构造器", spiClass, spiClass, e);
            throw new ConfigIllegalException(
                "构造实例[" + spiClass + "]失败，可能是没有无参数构造器，请为类[" + spiClass + "]增加无参数构造器", e);
        }
    }
}
