/**
 * Copyright 2009-2018 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sparrow.orm.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author harry
 */
public class ByteTypeHandler implements TypeHandler<Byte> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Byte parameter) throws SQLException {
        ps.setByte(i, parameter);
    }

    @Override
    public Byte getResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getByte(columnName);
    }

    @Override
    public Byte getResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getByte(columnIndex);
    }

    @Override
    public Byte getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getByte(columnIndex);
    }
}
