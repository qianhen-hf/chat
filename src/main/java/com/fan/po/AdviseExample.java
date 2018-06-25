package com.fan.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AdviseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdviseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAccuserIdIsNull() {
            addCriterion("accuser_id is null");
            return (Criteria) this;
        }

        public Criteria andAccuserIdIsNotNull() {
            addCriterion("accuser_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccuserIdEqualTo(Long value) {
            addCriterion("accuser_id =", value, "accuserId");
            return (Criteria) this;
        }

        public Criteria andAccuserIdNotEqualTo(Long value) {
            addCriterion("accuser_id <>", value, "accuserId");
            return (Criteria) this;
        }

        public Criteria andAccuserIdGreaterThan(Long value) {
            addCriterion("accuser_id >", value, "accuserId");
            return (Criteria) this;
        }

        public Criteria andAccuserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("accuser_id >=", value, "accuserId");
            return (Criteria) this;
        }

        public Criteria andAccuserIdLessThan(Long value) {
            addCriterion("accuser_id <", value, "accuserId");
            return (Criteria) this;
        }

        public Criteria andAccuserIdLessThanOrEqualTo(Long value) {
            addCriterion("accuser_id <=", value, "accuserId");
            return (Criteria) this;
        }

        public Criteria andAccuserIdIn(List<Long> values) {
            addCriterion("accuser_id in", values, "accuserId");
            return (Criteria) this;
        }

        public Criteria andAccuserIdNotIn(List<Long> values) {
            addCriterion("accuser_id not in", values, "accuserId");
            return (Criteria) this;
        }

        public Criteria andAccuserIdBetween(Long value1, Long value2) {
            addCriterion("accuser_id between", value1, value2, "accuserId");
            return (Criteria) this;
        }

        public Criteria andAccuserIdNotBetween(Long value1, Long value2) {
            addCriterion("accuser_id not between", value1, value2, "accuserId");
            return (Criteria) this;
        }

        public Criteria andAccuserTypeIsNull() {
            addCriterion("accuser_type is null");
            return (Criteria) this;
        }

        public Criteria andAccuserTypeIsNotNull() {
            addCriterion("accuser_type is not null");
            return (Criteria) this;
        }

        public Criteria andAccuserTypeEqualTo(Integer value) {
            addCriterion("accuser_type =", value, "accuserType");
            return (Criteria) this;
        }

        public Criteria andAccuserTypeNotEqualTo(Integer value) {
            addCriterion("accuser_type <>", value, "accuserType");
            return (Criteria) this;
        }

        public Criteria andAccuserTypeGreaterThan(Integer value) {
            addCriterion("accuser_type >", value, "accuserType");
            return (Criteria) this;
        }

        public Criteria andAccuserTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("accuser_type >=", value, "accuserType");
            return (Criteria) this;
        }

        public Criteria andAccuserTypeLessThan(Integer value) {
            addCriterion("accuser_type <", value, "accuserType");
            return (Criteria) this;
        }

        public Criteria andAccuserTypeLessThanOrEqualTo(Integer value) {
            addCriterion("accuser_type <=", value, "accuserType");
            return (Criteria) this;
        }

        public Criteria andAccuserTypeIn(List<Integer> values) {
            addCriterion("accuser_type in", values, "accuserType");
            return (Criteria) this;
        }

        public Criteria andAccuserTypeNotIn(List<Integer> values) {
            addCriterion("accuser_type not in", values, "accuserType");
            return (Criteria) this;
        }

        public Criteria andAccuserTypeBetween(Integer value1, Integer value2) {
            addCriterion("accuser_type between", value1, value2, "accuserType");
            return (Criteria) this;
        }

        public Criteria andAccuserTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("accuser_type not between", value1, value2, "accuserType");
            return (Criteria) this;
        }

        public Criteria andAccuserdIdIsNull() {
            addCriterion("accuserd_id is null");
            return (Criteria) this;
        }

        public Criteria andAccuserdIdIsNotNull() {
            addCriterion("accuserd_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccuserdIdEqualTo(Long value) {
            addCriterion("accuserd_id =", value, "accuserdId");
            return (Criteria) this;
        }

        public Criteria andAccuserdIdNotEqualTo(Long value) {
            addCriterion("accuserd_id <>", value, "accuserdId");
            return (Criteria) this;
        }

        public Criteria andAccuserdIdGreaterThan(Long value) {
            addCriterion("accuserd_id >", value, "accuserdId");
            return (Criteria) this;
        }

        public Criteria andAccuserdIdGreaterThanOrEqualTo(Long value) {
            addCriterion("accuserd_id >=", value, "accuserdId");
            return (Criteria) this;
        }

        public Criteria andAccuserdIdLessThan(Long value) {
            addCriterion("accuserd_id <", value, "accuserdId");
            return (Criteria) this;
        }

        public Criteria andAccuserdIdLessThanOrEqualTo(Long value) {
            addCriterion("accuserd_id <=", value, "accuserdId");
            return (Criteria) this;
        }

        public Criteria andAccuserdIdIn(List<Long> values) {
            addCriterion("accuserd_id in", values, "accuserdId");
            return (Criteria) this;
        }

        public Criteria andAccuserdIdNotIn(List<Long> values) {
            addCriterion("accuserd_id not in", values, "accuserdId");
            return (Criteria) this;
        }

        public Criteria andAccuserdIdBetween(Long value1, Long value2) {
            addCriterion("accuserd_id between", value1, value2, "accuserdId");
            return (Criteria) this;
        }

        public Criteria andAccuserdIdNotBetween(Long value1, Long value2) {
            addCriterion("accuserd_id not between", value1, value2, "accuserdId");
            return (Criteria) this;
        }

        public Criteria andAccuserdTypeIsNull() {
            addCriterion("accuserd_type is null");
            return (Criteria) this;
        }

        public Criteria andAccuserdTypeIsNotNull() {
            addCriterion("accuserd_type is not null");
            return (Criteria) this;
        }

        public Criteria andAccuserdTypeEqualTo(Integer value) {
            addCriterion("accuserd_type =", value, "accuserdType");
            return (Criteria) this;
        }

        public Criteria andAccuserdTypeNotEqualTo(Integer value) {
            addCriterion("accuserd_type <>", value, "accuserdType");
            return (Criteria) this;
        }

        public Criteria andAccuserdTypeGreaterThan(Integer value) {
            addCriterion("accuserd_type >", value, "accuserdType");
            return (Criteria) this;
        }

        public Criteria andAccuserdTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("accuserd_type >=", value, "accuserdType");
            return (Criteria) this;
        }

        public Criteria andAccuserdTypeLessThan(Integer value) {
            addCriterion("accuserd_type <", value, "accuserdType");
            return (Criteria) this;
        }

        public Criteria andAccuserdTypeLessThanOrEqualTo(Integer value) {
            addCriterion("accuserd_type <=", value, "accuserdType");
            return (Criteria) this;
        }

        public Criteria andAccuserdTypeIn(List<Integer> values) {
            addCriterion("accuserd_type in", values, "accuserdType");
            return (Criteria) this;
        }

        public Criteria andAccuserdTypeNotIn(List<Integer> values) {
            addCriterion("accuserd_type not in", values, "accuserdType");
            return (Criteria) this;
        }

        public Criteria andAccuserdTypeBetween(Integer value1, Integer value2) {
            addCriterion("accuserd_type between", value1, value2, "accuserdType");
            return (Criteria) this;
        }

        public Criteria andAccuserdTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("accuserd_type not between", value1, value2, "accuserdType");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andAccTimeIsNull() {
            addCriterion("acc_time is null");
            return (Criteria) this;
        }

        public Criteria andAccTimeIsNotNull() {
            addCriterion("acc_time is not null");
            return (Criteria) this;
        }

        public Criteria andAccTimeEqualTo(Date value) {
            addCriterionForJDBCDate("acc_time =", value, "accTime");
            return (Criteria) this;
        }

        public Criteria andAccTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("acc_time <>", value, "accTime");
            return (Criteria) this;
        }

        public Criteria andAccTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("acc_time >", value, "accTime");
            return (Criteria) this;
        }

        public Criteria andAccTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("acc_time >=", value, "accTime");
            return (Criteria) this;
        }

        public Criteria andAccTimeLessThan(Date value) {
            addCriterionForJDBCDate("acc_time <", value, "accTime");
            return (Criteria) this;
        }

        public Criteria andAccTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("acc_time <=", value, "accTime");
            return (Criteria) this;
        }

        public Criteria andAccTimeIn(List<Date> values) {
            addCriterionForJDBCDate("acc_time in", values, "accTime");
            return (Criteria) this;
        }

        public Criteria andAccTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("acc_time not in", values, "accTime");
            return (Criteria) this;
        }

        public Criteria andAccTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("acc_time between", value1, value2, "accTime");
            return (Criteria) this;
        }

        public Criteria andAccTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("acc_time not between", value1, value2, "accTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUdpateTimeIsNull() {
            addCriterion("udpate_time is null");
            return (Criteria) this;
        }

        public Criteria andUdpateTimeIsNotNull() {
            addCriterion("udpate_time is not null");
            return (Criteria) this;
        }

        public Criteria andUdpateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("udpate_time =", value, "udpateTime");
            return (Criteria) this;
        }

        public Criteria andUdpateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("udpate_time <>", value, "udpateTime");
            return (Criteria) this;
        }

        public Criteria andUdpateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("udpate_time >", value, "udpateTime");
            return (Criteria) this;
        }

        public Criteria andUdpateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("udpate_time >=", value, "udpateTime");
            return (Criteria) this;
        }

        public Criteria andUdpateTimeLessThan(Date value) {
            addCriterionForJDBCDate("udpate_time <", value, "udpateTime");
            return (Criteria) this;
        }

        public Criteria andUdpateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("udpate_time <=", value, "udpateTime");
            return (Criteria) this;
        }

        public Criteria andUdpateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("udpate_time in", values, "udpateTime");
            return (Criteria) this;
        }

        public Criteria andUdpateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("udpate_time not in", values, "udpateTime");
            return (Criteria) this;
        }

        public Criteria andUdpateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("udpate_time between", value1, value2, "udpateTime");
            return (Criteria) this;
        }

        public Criteria andUdpateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("udpate_time not between", value1, value2, "udpateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}