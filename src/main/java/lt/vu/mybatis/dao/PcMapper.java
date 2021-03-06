package lt.vu.mybatis.dao;

import java.util.List;
import lt.vu.mybatis.model.Pc;
import org.mybatis.cdi.Mapper;

@Mapper
public interface PcMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PC
     *
     * @mbg.generated Mon May 03 19:03:24 EEST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PC
     *
     * @mbg.generated Mon May 03 19:03:24 EEST 2021
     */
    int insert(Pc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PC
     *
     * @mbg.generated Mon May 03 19:03:24 EEST 2021
     */
    Pc selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PC
     *
     * @mbg.generated Mon May 03 19:03:24 EEST 2021
     */
    List<Pc> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PC
     *
     * @mbg.generated Mon May 03 19:03:24 EEST 2021
     */
    int updateByPrimaryKey(Pc record);
}