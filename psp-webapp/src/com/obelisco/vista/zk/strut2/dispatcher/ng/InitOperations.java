/*
 * $Id: DefaultActionSupport.java 651946 2008-04-27 13:41:38Z apetrelli $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.obelisco.vista.zk.strut2.dispatcher.ng;

import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.StaticContentLoader;
import org.apache.struts2.dispatcher.ng.HostConfig;
import org.apache.struts2.util.ClassLoaderUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

/**
 * Contains initialization operations
 */
public class InitOperations {

    public InitOperations() {
    }

    /**
     * Initializes the internal Struts logging
     */
    public void initLogging(HostConfig filterConfig) {
        String factoryName = filterConfig.getInitParameter("loggerFactory");
        if (factoryName != null) {
            try {
                Class cls = ClassLoaderUtils.loadClass(factoryName, this.getClass());
                LoggerFactory fac = (LoggerFactory) cls.newInstance();
                LoggerFactory.setLoggerFactory(fac);
            } catch (InstantiationException e) {
                System.err.println("Unable to instantiate logger factory: " + factoryName + ", using default");
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                System.err.println("Unable to access logger factory: " + factoryName + ", using default");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.err.println("Unable to locate logger factory class: " + factoryName + ", using default");
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates and initializes the dispatcher
     */
    public Dispatcher initDispatcher(HostConfig filterConfig) {
        Dispatcher dispatcher = createDispatcher(filterConfig);
        dispatcher.init();
        return dispatcher;
    }

    /**
     * Initializes the static content loader with the filter configuration
     */
    public StaticContentLoader initStaticContentLoader(HostConfig filterConfig, Dispatcher dispatcher) {
        StaticContentLoader loader = dispatcher.getContainer().getInstance(StaticContentLoader.class);
        loader.setHostConfig(filterConfig);
        return loader;
    }

    /**
     * @return The dispatcher on the thread.
     * @throws IllegalStateException If there is no dispatcher available
     */
    public Dispatcher findDispatcherOnThread() {
        Dispatcher dispatcher = Dispatcher.getInstance();
        if (dispatcher == null) {
            throw new IllegalStateException("Must have the StrutsPrepareFilter execute before this one");
        }
        return dispatcher;
    }

    /**
     * Create a {@link Dispatcher} 
     */
    private Dispatcher createDispatcher(HostConfig filterConfig) {
        Map<String, String> params = new HashMap<String, String>();
        for (Iterator e = filterConfig.getInitParameterNames(); e.hasNext();) {
            String name = (String) e.next();
            String value = filterConfig.getInitParameter(name);
            params.put(name, value);
        }
        return new com.obelisco.vista.zk.strut2.dispatcher.Dispatcher(filterConfig.getServletContext(), params);
    }

    public void cleanup() {
        ActionContext.setContext(null);
    }
}
