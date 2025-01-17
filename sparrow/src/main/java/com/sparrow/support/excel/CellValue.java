/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sparrow.support.excel;

import com.sparrow.enums.DataType;

/**
 * created by harry on 2016/3/12. 单元格值(供应用层使用，可以直接入库操作)
 *
 * @author harry
 */
public class CellValue {
    private DataType dataType;
    private Object value;
    private String excelTitle;

    CellValue(DataType dataType, Object content, String excelTitle) {
        this.dataType = dataType;
        this.value = content;
        this.excelTitle = excelTitle;
    }

    public DataType getDataType() {
        return dataType;
    }

    public Object getValue() {
        return value;
    }

    public String getExcelTitle() {
        return excelTitle;
    }
}
