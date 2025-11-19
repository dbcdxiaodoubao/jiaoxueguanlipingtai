package com.mashang.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mashang.domain.entity.Class;
import com.mashang.domain.entity.Test;
import com.mashang.domain.entity.TestAnswer;
import com.mashang.domain.entity.TestClass;
import com.mashang.domain.vo.management.TeacherDtlVo;
import com.mashang.domain.vo.management.TeacherListVo;
import com.mashang.domain.vo.teacher.*;
import com.mashang.mapper.*;
import com.mashang.service.ITeacherServicee;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, SysUser>
        implements ITeacherServicee {

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    private ClassMapper classMapper;
    
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TestClassMapper testClassMapper;

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private TestAnswerMapper testAnswerMapper;

    @Override
    public List<TeacherListVo> list(String nickName) {
        return teacherMapper.list(nickName);
    }

    @Override
    public TeacherDtlVo selectByid(Long id) {
        return teacherMapper.selectByid(id);
    }

    /**
     * 查询班级总数，班级总人数，试卷总数，题目总数
     * @return
     */
    @Override
    public TotalVo total() {
        Long classUserCount=0L;
        int questionCount=0;
        //查询该教师管理班级总数
        Long classCount = classMapper.selectCount(new LambdaQueryWrapper<Class>()
                .eq(Class::getTeacherId, SecurityUtils.getUserId()));

        //查询该教师管理班级总人数
        //1. 查询该教师管理班级的id集合
        List<Integer> classIds = classMapper.selectClassIds(SecurityUtils.getUserId());
        //2. 通过班级id集合查询班级总人数
        if (ObjectUtil.isNotEmpty(classIds)){
            classUserCount = studentMapper.selectCount(new LambdaQueryWrapper<SysUser>().in(SysUser::getClassId, classIds));
        }

        //查询该教师管理班级的班级试卷总数
        List<TestClass> testClasses = testClassMapper.selectList(new LambdaQueryWrapper<TestClass>().in(TestClass::getClassId, classIds));
        Integer testCount = testClasses.size();

        //查询该教师管理班级的班级试卷的题目总数
        //1.获得班级试卷id集合
        List<Integer> testIds = testClasses.stream().map(TestClass::getTestId).collect(Collectors.toList());
        //2.获取题目总和
        if (ObjectUtil.isNotEmpty(testIds)){
            List<Object> questionNums = testMapper.selectObjs(new LambdaQueryWrapper<Test>()
                    .select(Test::getQuestionNum).in(Test::getTestId, testIds));
            questionCount = questionNums.stream().map(Integer.class::cast).mapToInt(Integer::intValue).sum();
        }
        return new TotalVo(classCount.intValue(), classUserCount.intValue(), testCount, questionCount);
    }

    /**
     * 查询班级人数分布
     * @return
     */
    @Override
    public List<ClassSizeDistributionVo> classSizeDistribution() {
        List<Integer> classIds = classMapper.selectClassIds(SecurityUtils.getUserId());
        if(ObjectUtil.isEmpty(classIds))return Collections.emptyList();
        return baseMapper.classSizeDistribution(classIds);
    }

    /**
     * 查询班级试卷分布
     * @return
     */
    @Override
    public List<ClassTestDistributionVo> classTestDistribution() {
        List<Integer> classIds = classMapper.selectClassIds(SecurityUtils.getUserId());
        if(ObjectUtil.isEmpty(classIds))return Collections.emptyList();
        return baseMapper.classTestDistribution(classIds);
    }

    /**
     * 获取班级试卷平均分
     * @return
     */
    @Override
    public List<TestAverageVo> testAverage() {
        List<TestAverageVo> result = new ArrayList<>();
        //查询当前教师管理班级id集合
        List<Integer> classIds = classMapper.selectClassIds(SecurityUtils.getUserId());
        if(ObjectUtil.isEmpty(classIds))return Collections.emptyList();
        //根据班级id查询班级平均分
        for (Integer classId : classIds) {
            //根据班级id查询学生id集合
            List<Integer> userIds = studentMapper.selectUserIds(classId);
            //判断班级学生是否为空
            if (ObjectUtil.isEmpty(userIds)) {
                result.add(new TestAverageVo(classMapper.selectById(classId).getClassName(), 0.00));
                continue;
            }
            //根据学生id集合查询答卷id集合
            List<Integer> testAnswerIds =testAnswerMapper.selectIdsByUserIds(userIds);
            //判断学生答卷是否为空
            if (ObjectUtil.isEmpty(testAnswerIds)) {
                result.add(new TestAverageVo(classMapper.selectById(classId).getClassName(), 0.00));
                continue;
            }
            //根据答卷id集合查询答题总分
            Integer scoreSum =testAnswerMapper.getSumScore(testAnswerIds);
            result.add(new TestAverageVo(classMapper.selectById(classId).getClassName(),
                    ((int)(scoreSum*100.0/testAnswerIds.size()))/100.0));

        }
        return result;
    }

    /**
     * 获取班级学生平均分
     * @param classId
     * @return
     */
    @Override
    public List<StudentAverageVo> studentAverage(Integer classId) {
        List<StudentAverageVo> result = new ArrayList<>();
        //根据班级id查询学生id集合
        List<Integer> userIds = studentMapper.selectUserIds(classId);
        for (Integer userId : userIds) {
          //根据学生id查询答卷集合
          List<Integer> testAnswerIds = testAnswerMapper.selectIdsByUserId(userId);
          if(ObjectUtil.isEmpty(testAnswerIds)){
              result.add(new StudentAverageVo(studentMapper.selectNickNameById(userId), 0.00));
              continue;
          }
          //根据答卷id集合查询答题总分
            Integer scoreSum = testAnswerMapper.getSumScore(testAnswerIds);
            result.add(new StudentAverageVo(studentMapper.selectNickNameById(userId),
                    ((int)(scoreSum*100.0/testAnswerIds.size()))/100.0));
        }
        return result;
    }
}
