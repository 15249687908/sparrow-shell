/*
 * Copyright 2002-2012 the original author or authors.
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

package com.sparrow.xml;

import org.slf4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleSaxErrorHandler implements ErrorHandler {

    private final Logger logger;

    public SimpleSaxErrorHandler(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void warning(SAXParseException ex) throws SAXException {
        logger.warn("Ignored XML validation warning", ex);
    }

    @Override
    public void error(SAXParseException ex) throws SAXException {
        logger.error("sax parse exception", ex);
        throw ex;
    }

    @Override
    public void fatalError(SAXParseException ex) throws SAXException {
        logger.error("sax parse exception", ex);
        throw ex;
    }

}
