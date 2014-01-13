/*
 * Copyright 1999-2011 Alibaba Group Holding Ltd.
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
package com.alibaba.druid.bvt.filter.wall;

import com.alibaba.druid.wall.WallConfig.TenantCallBack;

public class TenantTestCallBack implements TenantCallBack {

    @Override
    public Object getTenantValue(StatementType statementType, String tableName) {
        return 123;
    }

    @Override
    public String getTenantColumn(StatementType statementType, String tableName) {
        return "tenant";
    }

    @Override
    public String getHiddenColumn(String tableName) {
        return "tenant";
    }

    @Override
    public void resultset_hiddenColumn(Object value) {
        System.out.println(value);
    }
}