package com.psf.core.mapper;

import com.psf.core.model.Target;
import com.psf.core.model.TargetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TargetMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table target
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    long countByExample(TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table target
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    int deleteByExample(TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table target
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    int deleteByPrimaryKey(Integer tid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table target
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    int insert(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table target
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    int insertSelective(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table target
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    List<Target> selectByExampleWithBLOBs(TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table target
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    List<Target> selectByExample(TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table target
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    Target selectByPrimaryKey(Integer tid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table target
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    int updateByExampleSelective(@Param("record") Target record, @Param("example") TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table target
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") Target record, @Param("example") TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table target
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    int updateByExample(@Param("record") Target record, @Param("example") TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table target
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    int updateByPrimaryKeySelective(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table target
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table target
     *
     * @mbg.generated Mon Jul 13 15:32:10 CST 2020
     */
    int updateByPrimaryKey(Target record);
}