/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.seam.exception.control;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Entry point event into the Catch system.  This object is nearly immutable, the only mutable portion
 * is the handled flag.
 */
public class ExceptionToCatch implements Serializable {
    private static final long serialVersionUID = 2629791852079147814L;

    private Throwable exception;
    private boolean handled;
    private transient Set<Annotation> qualifiers;

    public ExceptionToCatch() {
    } // needed to be a bean

    /**
     * Constructor that adds qualifiers for the handler(s) to run.
     * Typically only integrators will be using this constructor.
     *
     * @param exception  Exception to handle
     * @param qualifiers qualifiers to use to narrow the handlers called
     */
    public ExceptionToCatch(Throwable exception, Annotation... qualifiers) {
        this.exception = exception;
        this.qualifiers = new HashSet<Annotation>();
        Collections.addAll(this.qualifiers, qualifiers);
    }

    /**
     * Basic constructor without any qualifiers defined.
     *
     * @param exception Exception to handle.
     */
    public ExceptionToCatch(Throwable exception) {
        this.exception = exception;
        this.qualifiers = Collections.emptySet();
    }

    public Throwable getException() {
        return exception;
    }

    /**
     * NOTE: Will be moved to protected in a later release!!
     */
    // TODO: Move to protected
    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    public boolean isHandled() {
        return handled;
    }

    public Set<Annotation> getQualifiers() {
        return Collections.unmodifiableSet(qualifiers);
    }
}
