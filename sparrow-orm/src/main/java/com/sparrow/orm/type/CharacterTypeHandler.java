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
public class CharacterTypeHandler implements TypeHandler<Character> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Character parameter) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    @Override
    public Character getResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName);
        if (columnValue != null) {
            return columnValue.charAt(0);
        }
        return null;
    }

    @Override
    public Character getResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValue = rs.getString(columnIndex);
        if (columnValue != null) {
            return columnValue.charAt(0);
        }
        return null;
    }

    @Override
    public Character getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String columnValue = cs.getString(columnIndex);
        if (columnValue != null) {
            return columnValue.charAt(0);
        }
        return null;
    }
}
