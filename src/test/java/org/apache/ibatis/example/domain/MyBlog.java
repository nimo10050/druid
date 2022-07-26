/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.example.domain;



public class MyBlog {

  private int id;
  private int viewCounts;
  private Integer likeCounts;
  private String title;

  public MyBlog() {

  }
  public MyBlog(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getViewCounts() {
    return viewCounts;
  }

  public void setViewCounts(int viewCounts) {
    this.viewCounts = viewCounts;
  }

  public Integer getLikeCounts() {
    return likeCounts;
  }

  public void setLikeCounts(Integer likeCounts) {
    this.likeCounts = likeCounts;
  }

  @Override
  public String toString() {
    return "MyBlog{" +
            "id=" + id +
            ", viewCounts=" + viewCounts +
            ", likeCounts=" + likeCounts +
            ", title='" + title + '\'' +
            '}';
  }
}
