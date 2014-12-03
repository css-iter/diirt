/**
 * Copyright (C) 2010-14 diirt developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.diirt.service.exec;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import org.diirt.service.AbstractFileServiceProvider;
import org.diirt.service.Service;
import org.diirt.util.config.Configuration;

/**
 * A service factory that crawls a directory for xml files, and creates an exec
 * service from each of them.
 *
 * @author carcassi
 */
public class ExecServiceProvider extends AbstractFileServiceProvider {

    private static final Logger log = Logger.getLogger(ExecServiceProvider.class.getName());

    private final boolean includeGenericExecService;

    /**
     * Creates a new factory that reads from the given directory.
     * <p>
     * If the directory does not exist, it simply returns an empty set.
     *
     * @param directory a directory
     * @param includeGenericExecService whether to include the general purpose
     * exec service
     */
    public ExecServiceProvider(File directory, boolean includeGenericExecService) {
        super(directory);
        this.includeGenericExecService = includeGenericExecService;
    }

    /**
     * Creates a new factory using the default configuration directory.
     */
    public ExecServiceProvider() {
        this(new File(Configuration.getDirectory(), "services/exec"), true);
    }

    @Override
    public String getName() {
        return "exec";
    }

    @Override
    public Service createService(File file) throws Exception {
        if (file.getName().endsWith(".xml")) {
            return ExecServices.createFromXml(new FileInputStream(file));
        } else {
            return null;
        }
    }

    @Override
    public Collection<Service> additionalServices() {
        if (includeGenericExecService) {
            return Collections.<Service>singleton(new GenericExecService());
        } else {
            return Collections.emptySet();
        }
    }

}