package kr.or.connect.vaccine.dao;

public class MemberDaoSqls {

	public static final String SELECT_ALL ="SELECT member_id, member_age, member_sex, member_vac, member_date FROM member order by member_id";
	public static final String UPDATE ="UPDATE member set member_age=:age, member_sex=:sex, member_vac=:vac, member_date=:date where member_id =:memberId";
	public static final String SELECT_BY_Member_ID ="SELECT member_id, member_age, member_sex, member_vac, member_date FROM member where member_id= :memberId";
	public static final String DELETE_BY_Member_ID="DELETE FROM member where member_id= :memberId";
	//실제 전체를 쓸때 * 쓰지말고 일일이 나열하는 것이 더 명확하다.
	
	public static final String SELECT_PAGING = "SELECT member_id, member_age, member_sex, member_vac, member_date FROM member ORDER BY member_id DESC limit :start, :limit";
	//mysql query 중 limit를 이용하면 시작 값, 끝날 값 특정한 부분만 select해오는 것 가능하다.
	public static final String SELECT_COUNT = "SELECT count(*) FROM member";
	public static final String SELECT_COUNT_BY_MEMBER_DATE ="SELECT count(*) FROM member where member_date <:member_date";

	//통계처리를 위한 파일
	public static final String SELECT_COUNT_BY_CONDITION ="SELECT COUNT(*) FROM member where member_age= :memberAge && member_sex= :memberSex && member_vac= :memberVac &&(member_date>=:startDate && member_date<=:endDate)";
	//몇 명인지 알아내는 쿼리
	public static final String SELECT_BY_CONDITION_PAGING ="SELECT member_id, member_age, member_sex, member_vac, member_date FROM member where member_age= :memberAge && member_sex= :memberSex && member_vac= :memberVac &&(member_date>=:startDate && member_date<=:endDate) ORDER BY member_id DESC limit :start, :limit";

	public static final String SELECT_COUNT_BY_TERM="SELECT COUNT(*) FROM member where member_date>=:startDate && member_date<=:endDate";
	public static final String SELECT_BY_TERM="SELECT member_id, member_age, member_sex, member_vac, member_date FROM member where member_date>=:startDate && member_date<=:endDate ORDER BY member_id DESC limit :start, :limit";
	
	public static final String SELECT_COUNT_WITHOUT_VAC="SELECT COUNT(*) FROM member where member_vac= :memberVac &&(member_date>=:startDate && member_date<=:endDate)";
	public static final String SELECT_ALL_WITHOUT_VAC="SELECT member_id, member_age, member_sex, member_vac, member_date FROM member where member_vac= :memberVac && (member_date>=:startDate && member_date<=:endDate) ORDER BY member_id DESC limit :start, :limit";
	
	public static final String SELECT_COUNT_WITHOUT_SEX="SELECT COUNT(*) FROM member where member_sex= :memberSex &&(member_date>=:startDate && member_date<=:endDate)";
	public static final String SELECT_ALL_WITHOUT_SEX="SELECT member_id, member_age, member_sex, member_vac, member_date FROM member where member_sex= :memberSex && (member_date>=:startDate && member_date<=:endDate) ORDER BY member_id DESC limit :start, :limit";
	
	public static final String SELECT_COUNT_WITHOUT_AGE="SELECT COUNT(*) FROM member where member_age= :memberAge &&(member_date>=:startDate && member_date<=:endDate)";
	public static final String SELECT_ALL_WITHOUT_AGE="SELECT member_id, member_age, member_sex, member_vac, member_date FROM member where member_age= :memberAge && (member_date>=:startDate && member_date<=:endDate) ORDER BY member_id DESC limit :start, :limit";
	

	public static final String SELECT_COUNT_WITHOUT_SEX_AND_VAC ="SELECT COUNT(*) FROM member where member_sex= :memberSex && member_vac= :memberVac &&(member_date>=:startDate && member_date<=:endDate)";
	public static final String SELECT_ALL_WITHOUT_SEX_AND_VAC ="SELECT member_id, member_age, member_sex, member_vac, member_date FROM member where member_sex= :memberSex && member_vac= :memberVac &&(member_date>=:startDate && member_date<=:endDate) ORDER BY member_id DESC limit :start, :limit";

	public static final String SELECT_COUNT_WITHOUT_AGE_AND_SEX ="SELECT COUNT(*) FROM member where member_age= :memberAge && member_sex= :memberSex &&(member_date>=:startDate && member_date<=:endDate)";
	public static final String SELECT_ALL_WITHOUT_AGE_AND_SEX ="SELECT member_id, member_age, member_sex, member_vac, member_date FROM member where member_age= :memberAge && member_sex= :memberSex &&(member_date>=:startDate && member_date<=:endDate) ORDER BY member_id DESC limit :start, :limit";

	public static final String SELECT_COUNT_WITHOUT_AGE_AND_VAC ="SELECT COUNT(*) FROM member where member_age= :memberAge && member_vac= :memberVac &&(member_date>=:startDate && member_date<=:endDate)";
	public static final String SELECT_ALL_WITHOUT_AGE_AND_VAC ="SELECT member_id, member_age, member_sex, member_vac, member_date FROM member where member_age= :memberAge && member_vac= :memberVac &&(member_date>=:startDate && member_date<=:endDate) ORDER BY member_id DESC limit :start, :limit";

	
	public static final String SELECT_COUNT_BY_SQL="SELECT COUNT(*) FROM member where :sql && member_date>=:startDate && member_date<=:endDate";
}
