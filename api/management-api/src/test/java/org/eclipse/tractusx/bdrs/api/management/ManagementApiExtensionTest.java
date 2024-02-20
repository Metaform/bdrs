/*
 *  Copyright (c) 2024 Bayerische Motoren Werke Aktiengesellschaft (BMW AG)
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Bayerische Motoren Werke Aktiengesellschaft (BMW AG) - initial API and implementation
 *
 */

package org.eclipse.tractusx.bdrs.api.management;

import org.eclipse.edc.junit.extensions.DependencyInjectionExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;
import org.eclipse.edc.web.spi.WebServer;
import org.eclipse.edc.web.spi.WebService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.eclipse.tractusx.bdrs.api.management.ManagementApiExtension.CONTEXT_NAME;
import static org.eclipse.tractusx.bdrs.api.management.ManagementApiExtension.PATH;
import static org.eclipse.tractusx.bdrs.api.management.ManagementApiExtension.PORT;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 */
@ExtendWith(DependencyInjectionExtension.class)
class ManagementApiExtensionTest {

    private WebServer webServer;
    private WebService webService;

    @Test
    void verifyBoot(ManagementApiExtension extension, ServiceExtensionContext context) {
        extension.initialize(context);

        verify(webServer).addPortMapping(CONTEXT_NAME, PORT, PATH);
        verify(webService).registerResource(eq(CONTEXT_NAME), isA(ManagementApiController.class));
    }

    @BeforeEach
    void setUp(ServiceExtensionContext context) {
        webServer = mock(WebServer.class);
        context.registerService(WebServer.class, webServer);

        webService = mock(WebService.class);
        context.registerService(WebService.class, webService);
    }

}
