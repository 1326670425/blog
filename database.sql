create table if not exists achievement_type
(
    id        bigint auto_increment comment '自增主键'
        primary key,
    type_name varchar(50) default '' null comment '荣誉类型，比如论文，专利等'
)
    comment '荣誉类型';

create table if not exists article
(
    id          bigint auto_increment comment '自增主键'
        primary key,
    user_id     bigint                  not null comment '作者id',
    title       varchar(100) default '' not null comment '文章标题',
    description varchar(256) default '' not null comment '文章摘要',
    content     text                    not null comment '文章内容',
    create_time timestamp               null comment '发表时间',
    update_time timestamp               null comment '最近更新时间',
    deleted     tinyint      default 0  not null comment '逻辑删除',
    type        tinyint      default 0  not null comment '编辑器类型，0：md，1：富文本',
    status      tinyint      default 0  not null comment '文章状态，0：草稿，1：发布',
    like_num    int          default 0  not null comment '获赞个数'
)
    comment '文章表';

create table if not exists child_comment
(
    id          bigint auto_increment comment '自增主键'
        primary key,
    content     varchar(256) default '' not null comment '评论内容',
    parent_id   bigint                  not null comment '所属一级评论id',
    user_id     bigint                  not null comment '发表者id',
    create_time timestamp               null comment '发表时间',
    like_num    int          default 0  null comment '获赞数量',
    deleted     tinyint      default 0  null comment '逻辑删除'
)
    comment '二级评论表';

create table if not exists friend
(
    id           bigint auto_increment comment '自增主键'
        primary key,
    user_id_1    bigint                     not null comment '好友1ID',
    user_id_2    bigint                     not null comment '好友2ID',
    relation     int         default 0      null comment '状态字段，区分强、弱、单向好友关系，保留字段。',
    user_group_1 varchar(20) default '默认分组' not null comment '好友1方的分组',
    user_group_2 varchar(20) default '默认分组' not null comment '好友2方所在的分组',
    create_time  timestamp                  null comment '创建时间',
    update_time  timestamp                  null comment '更新时间',
    deleted      tinyint     default 0      null comment '逻辑删除'
)
    comment '好友表';

create table if not exists letter
(
    id          bigint auto_increment comment '自增主键'
        primary key,
    sender      bigint                  not null comment '发送者id',
    receiver    bigint                  not null comment '接收者id',
    content     varchar(256) default '' not null comment '留言内容',
    create_time timestamp               null comment '创建时间',
    deleted     tinyint      default 0  null comment '逻辑删除'
)
    comment '留言表';

create table if not exists message
(
    id          bigint auto_increment comment '自增主键'
        primary key,
    user_id     bigint                   not null comment '作者id',
    content     varchar(1024) default '' not null comment '消息内容',
    create_time timestamp                null comment '发表时间',
    update_time timestamp                null comment '最近更新时间',
    deleted     tinyint       default 0  not null comment '逻辑删除',
    like_num    int           default 0  not null comment '获赞个数'
)
    comment '短消息表';

create table if not exists persistent_logins
(
    username  varchar(64)                         not null,
    series    varchar(64)                         not null
        primary key,
    token     varchar(64)                         not null,
    last_used timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
);

create table if not exists research_group
(
    id          bigint auto_increment comment '自增id'
        primary key,
    name        varchar(50)  default '' null comment '名称',
    description varchar(256) default '' null comment '描述',
    leader      bigint                  null comment '组长，关联导师表id'
)
    comment '课题组';

create table if not exists resume_achievement
(
    id               bigint auto_increment comment '自增主键'
        primary key,
    ach_title        varchar(256) default ''      null comment '标题，描述，比如论文题目，奖项名称',
    user_id          bigint                       not null comment '关联用户',
    type_id          bigint                       not null comment '荣誉类型，关联achievement_type表',
    ach_time         date                         null comment '获得时间',
    ach_description  varchar(256) default ''      null comment '相关描述',
    ach_organization varchar(256) default ''      null comment '相关组织机构',
    deleted          tinyint      default 0       null comment '逻辑删除',
    language_type    varchar(20)  default 'zh_CN' null comment '语言'
)
    comment '个人荣誉';

create table if not exists resume_education
(
    id            bigint auto_increment comment '自增主键'
        primary key,
    user_id       bigint                       not null comment '所属用户id',
    school        varchar(256) default ''      null comment '学校',
    identity      varchar(256) default ''      null comment '身份，本科、硕士、访问学者等',
    start_time    date                         null comment '开始时间',
    end_time      date                         null comment '结束时间',
    language_type varchar(20)  default 'zh_CN' null comment '语言',
    deleted       tinyint      default 0       null comment '逻辑删除'
)
    comment '教育经历';

create table if not exists resume_general
(
    id              bigint auto_increment comment '自增主键'
        primary key,
    user_id         bigint                       null comment '用户id',
    real_name       varchar(256) default ''      null comment '姓名',
    birthday        date                         null comment '生日',
    profile_picture varchar(512) default ''      null comment '头像，证件照片',
    position        varchar(256) default ''      null comment '现职位',
    location        varchar(256) default ''      null comment '现居地',
    introduction    text                         null comment '个人简介',
    language_type   varchar(20)  default 'zh_CN' null comment '语言',
    deleted         tinyint      default 0       null comment '逻辑删除'
)
    comment '个人简历常规信息';

create table if not exists resume_work
(
    id            bigint auto_increment comment '自增主键'
        primary key,
    user_id       bigint                       not null comment '所属用户id',
    company       varchar(256) default ''      null comment '公司或企业',
    position      varchar(256) default ''      null comment '职位',
    start_time    date                         null comment '开始时间',
    end_time      date                         null comment '结束时间',
    language_type varchar(20)  default 'zh_CN' null comment '语言',
    deleted       tinyint      default 0       null comment '逻辑删除'
)
    comment '工作经历';

create table if not exists role
(
    id   bigint auto_increment comment '自增主键'
        primary key,
    name varchar(20) default '' not null comment '角色名'
)
    comment '角色表';

create table if not exists root_comment
(
    id          bigint auto_increment comment '自增主键'
        primary key,
    user_id     bigint                  not null comment '发表作者id',
    content     varchar(256) default '' not null comment '评论内容',
    like_num    int          default 0  not null comment '获赞数量',
    deleted     tinyint      default 0  not null comment '逻辑删除',
    create_time timestamp               null comment '发表时间',
    type        tinyint      default 0  not null comment '评论类型：文章评论或消息评论',
    host_id     bigint                  not null comment '所属消息或文章的id'
)
    comment '一级评论表';

create table if not exists student
(
    id                bigint auto_increment comment '自增主键'
        primary key,
    user_id           bigint      not null comment '关键基础用户表',
    title             varchar(20) null comment '身份，硕博等',
    tutor             bigint      null comment '关联导师表id',
    enrollment_time   date        null comment '入学年份',
    graduation_time   date        null comment '毕业年份',
    research_group_id bigint      not null comment '所在课题组id'
)
    comment '学生表';

create table if not exists teacher
(
    id                bigint auto_increment comment '自增主键'
        primary key,
    user_id           bigint                 not null comment '用户id，关联基础用户表user',
    title             varchar(20) default '' null comment '职称',
    research_group_id bigint                 not null comment '所在课题组id',
    constraint teacher_user_id_uindex
        unique (user_id)
)
    comment '导师表';

create table if not exists u2u_notify
(
    id          bigint auto_increment comment '自增主键'
        primary key,
    type        varchar(20)  default '' not null comment '通知消息类型，比如评论通知，回复通知等',
    sender      bigint                  not null comment '发送者id',
    sender_name varchar(20)  default '' null comment '发送者用户名',
    receiver    bigint                  not null comment '接收者id',
    target_id   bigint                  not null comment '目标id',
    target_desc varchar(50)  default '' null comment '目标对象的描述，比如文章标题',
    target_type varchar(20)  default '' null comment '目标对象类型，比如消息、文章',
    message     varchar(256) default '' null comment '消息内容，比如回复的内容，或者添加好友请求的验证消息',
    status      tinyint      default 0  null comment '消息状态，0未读，1已读',
    create_time timestamp               null comment '创建时间',
    update_time timestamp               null comment '更新时间',
    deleted     tinyint      default 0  null comment '逻辑删除'
)
    comment '用户对用户通知表';

create table if not exists user
(
    id          bigint auto_increment comment '自增主键'
        primary key,
    username    varchar(50)  default ''                not null comment '用户名',
    password    varchar(256) default ''                not null comment '密码',
    birthday    date                                   null comment '生日',
    avatar      varchar(256) default ''                not null comment '头像地址',
    gender      tinyint      default 0                 not null comment '性别',
    email       varchar(30)  default ''                not null comment '邮箱',
    tel_number  varchar(20)  default ''                not null comment '电话',
    create_time timestamp                              not null comment '创建时间',
    update_time timestamp                              not null comment '更新时间',
    deleted     tinyint      default 0                 not null comment '逻辑删除，默认0表示未删除',
    last_login  timestamp    default CURRENT_TIMESTAMP null comment '上次登录时间'
)
    comment '用户表';

create table if not exists user_role
(
    user_id bigint not null comment '用户id',
    role_id bigint not null comment '角色id'
        primary key
)
    comment '用户-角色表';


