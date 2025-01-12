/*
 * Copyright 1999-2017 Alibaba Group Holding Ltd.
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
package com.alibaba.druid.bvt.sql.mysql.select;

import com.alibaba.druid.sql.MysqlTest;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.util.JdbcConstants;

import java.text.SimpleDateFormat;
import java.util.List;

public class MySqlSelectTest_55 extends MysqlTest {
    public void test_date() throws Exception {
        System.out.println(new SimpleDateFormat("yyyyMMdd").parse("19050101").getTime());
        System.out.println(new SimpleDateFormat("yyyyMMdd").parse("10010101").toString());
    }

    public void test_0() throws Exception {
        String sql = "select count(0)\n" +
                "from tb_user\n" +
                "where\n" +
                "(login_name like '%j%' and v_sort between 1 and 10 )\n" +
                "or\n" +
                "(display_name like '%j%' and v_sort between 1 and 10 )";

        System.out.println(sql);


        List<SQLStatement> statementList = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL, true);
        SQLStatement stmt = statementList.get(0);

        assertEquals(1, statementList.size());

        SchemaStatVisitor visitor = SQLUtils.createSchemaStatVisitor(JdbcConstants.MYSQL);
        stmt.accept(visitor);

//        System.out.println("Tables : " + visitor.getTables());
        System.out.println("fields : " + visitor.getColumns());
//        System.out.println("coditions : " + visitor.getConditions());
//        System.out.println("orderBy : " + visitor.getOrderByColumns());

//        assertEquals(1, visitor.getTables().size());
//        assertEquals(1, visitor.getColumns().size());
//        assertEquals(0, visitor.getConditions().size());
//        assertEquals(0, visitor.getOrderByColumns().size());

        {
            String output = SQLUtils.toMySqlString(stmt);
            assertEquals("SELECT count(0)\n" +
                            "FROM tb_user\n" +
                            "WHERE (login_name LIKE '%j%'\n" +
                            "\t\tAND v_sort BETWEEN 1 AND 10)\n" +
                            "\tOR (display_name LIKE '%j%'\n" +
                            "\t\tAND v_sort BETWEEN 1 AND 10)", //
                    output);
        }
        {
            String output = SQLUtils.toMySqlString(stmt, SQLUtils.DEFAULT_LCASE_FORMAT_OPTION);
            assertEquals("select count(0)\n" +
                            "from tb_user\n" +
                            "where (login_name like '%j%'\n" +
                            "\t\tand v_sort between 1 and 10)\n" +
                            "\tor (display_name like '%j%'\n" +
                            "\t\tand v_sort between 1 and 10)", //
                    output);
        }

        {
            String output = SQLUtils.toMySqlString(stmt, new SQLUtils.FormatOption(true, true, true));
            assertEquals("SELECT count(0)\n" +
                            "FROM tb_user\n" +
                            "WHERE (login_name LIKE ?\n" +
                            "\t\tAND v_sort BETWEEN ? AND ?)\n" +
                            "\tOR (display_name LIKE ?\n" +
                            "\t\tAND v_sort BETWEEN ? AND ?)", //
                    output);
        }
    }
}
