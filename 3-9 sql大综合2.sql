--根据以下各表完成下面的题目 --sql大综合2
--出版社press表
drop table press;
create table press(id int primary key,--出版社编号
                    pname varchar2(30),--名称
                    address varchar2(50),--地址
                    phone varchar2(20),--电话
                    mail  varchar2(20));--邮箱
insert into press values(1001,'人民邮电出版社','北京市东城区夕照寺街14号','010-22312345','rmyd@163.com');
insert into press values(1002,'北京出版社','北京市北三环中路6号','010-298764563','bjcbc@163.com');
insert into press values(1003,'广东教育出版社','广州市越秀区环市东路472号','020-54329895',null);
insert into press values(1004,'清华大学出版社','清华大学学研大厦A座','010-87564839',null);
commit;
--作者 author表
drop table authors;
create table authors(id int primary key,--作者编号
                     sname varchar2(20),--姓名
                     phone varchar2(20),--电话
                     address varchar2(50)); --地址
insert into authors values(2001,'陈青云','15678943456','北京市顺义区');
insert into authors values(2002,'丁林','15834528764','广州市天河区');
insert into authors values(2003,'安迪','13823146542','重庆市榆林区');
insert into authors values(2004,'邓晓光','13976342134','湖南省长沙市');
insert into authors values(2005,'伍明','13298346542','四川省成都市');
insert into authors values(2006,'路遥','13309234523','陕西省榆林市');
insert into authors values(2007,'雨果','','法国贝桑松');
commit;
--书的种类 booktype表
drop table booktype;
create table booktype(tid varchar2(20) primary key,--种类编号
                      tname varchar2(20),--种类名称
                      loc varchar2(20));     --摆放位置
insert into booktype values('J001','计算机类','二楼1号柜');
insert into booktype values('Y001','医学类','三楼3号柜');
insert into booktype values('W001','文学类','二楼5号柜');
insert into booktype values('L001','历史类','二楼3号柜');
commit;
--书 book表
drop table book;
create table book(bno varchar2(20) primary key,--图书编号
                  bname varchar2(50),--图书名称
                  aid int,--作者
                  pid int,--出版社
                  tid varchar2(20),--种类
                  buy date,--进货日期
                  price number(7,2),--价格
                  buynum int); --数量 
insert into book values('J0001','计算机基础',2001,1001,'J001',date '2016-1-5',12.5,5);
insert into book values('J0002','oracle从入门到精通',2002,1004,'J001',date '2016-8-8',30,10);
insert into book values('Y0001','常见病例及用药',2005,1003,'Y001',date '2016-2-4',20,20);
insert into book values('W0001','平凡的世界',2006,1003,'W001',date '2016-5-15',35,30);
insert into book values('W0002','悲惨世界',2007,1004,'W001',date '2016-4-9',31,22);
insert into book values('J0003','SQL入门',2001,1004,'J001',date '2016-2-15',32,20);
insert into book values('J0004','SQL基础课程',2002,1001,'J001',date '2016-6-6',28,10);
commit;           
--销售记录 sales表
drop table sales;
create table sales(orderid  int primary key,--订单号
                   bno varchar2(20),--图书编号
                   selldate  date,--销售日期
                   sellnum   int);--销售数量
insert into sales values(10001,'J0001',date '2016-5-1',2);
insert into sales values(10002,'J0002',date '2016-9-6',1); 
insert into sales values(10003,'J0001',date '2016-2-5',1); 
insert into sales values(10004,'J0002',date '2016-10-1',1); 
insert into sales values(10005,'W0001',date '2016-6-18',5);
insert into sales values(10006,'J0003',date '2016-7-19',2);
commit; 
SELECT * FROM press; --出版社
SELECT * FROM authors; --作者
SELECT * FROM booktype; --书的种类
SELECT * FROM book;  --书(主表)
SELECT * FROM sales; --销售记录
(以下所有数据显示数据,无数据显示为0)
1.'人民邮电出版社'出版了哪些书？显示书名,作者名字,出版社,价格
select b.bname,b.aid,p.pname,b.price
from book b join press p on b.pid=p.id
where p.pname='人民邮电出版社'


2.'陈青云'写了哪些书？显示书编号,书名,作者,出版社,价格
select b.bno,b.bname,a.sname,p.pname,b.price
from book b join authors a on b.aid=a.id join press p on b.pid=p.id
where a.sname='陈青云'


3.每个出版社的书各出版了多少书？销售了多少本？销售额是多少？
  图书的均价是多少?
  显示出版社名,出版书的数量,销售书的数量,书的销售额,图书的均价
select p.pname 出版社名,count(1) 出版书的数量,sum(s.sellnum) 销售书的数量,sum(s.sellnum*b.price) 销售额,avg(price) 图书的均价
from book b  join sales s on b.bno = s.bno 
             join press p on b.pid=p.id
group by p.id,p.pname
  
4.每个人各写了几本书？销售了多少本？卖了多少钱？图书的均价是多少?
  显示作者名,书的数量,销售数量,销售金额,图书的均价
select a.sname 作者名,count(1) 书的数量,sum(s.sellnum) 销售金额,sum(b.price*s.sellnum) 销售金额,avg(b.price) 图书的均价
from book b join sales s on b.bno = s.bno join authors a on b.aid=a.id
group by b.aid,a.sname

  
5.每个作者每个月各销售了多少数量,多少金额？显示作者,月份,数量,金额
select a.sname 作者,to_char(s.selldate,'mm') 月份,sum(s.sellnum) 数量,sum(b.price*s.sellnum) 金额
from book b join sales s on b.bno = s.bno join authors a on b.aid=a.id
group by to_char(s.selldate,'mm'),a.sname

6.每个出版社每个月各销售了多少数量,多少金额？显示月份,出版社名,数量,金额
select to_char(s.selldate,'mm') 月份,p.pname 出版社名,sum(s.sellnum) 数量,sum(s.sellnum*b.price) 金额
from book b join sales s on b.bno = s.bno join press p on b.pid=p.id
group by to_char(s.selldate,'mm'),p.pname



7.每类图书各进货多少本？各剩多少数量？显示类别名称,进货量,剩余量
select bt.tname 类别名称,sum(b.buynum) 进货量,sum(b.buynum-s.sellnum) 剩余量
from book b join booktype bt on bt.tid=b.tid join sales s on b.bno=s.bno
group by bt.tid ,bt.tname


8.查进货后3各月内,一本都没卖出去的书？显示书的所有信息
select *
  from book
 where bno not in (select distinct b.bno
                     from book b
                     join sales s
                       on b.bno = s.bno
                    where add_months(s.selldate, -3) < b.buy)
                    


9.查'人民邮电出版社'出版的'计算机类'的名字中带'SQL'的图书,
  显示书名,类别名,出版社名,图书位置
select b.bname,bt.tname,p.pname,bt.loc
from book b join press p on b.pid = p.id join booktype bt on b.tid = bt.tid
where p.pname = '人民邮电出版社' and bt.tname = '计算机类' and b.bname like '%SQL%'


10.编写一个存储过程SP_PRESS，实现以下功能
①将雨果写的'悲惨的世界'这本书的价格改成56,pid改成'北京出版社'的pid,
  tid改成改成'二楼3号柜'的tid
②将计算机类,清华大学出版社的书的价格都增加10元
③删除名字叫'常见病例及用药'或价格小于20的书







