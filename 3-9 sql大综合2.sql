--�������¸�������������Ŀ --sql���ۺ�2
--������press��
drop table press;
create table press(id int primary key,--��������
                    pname varchar2(30),--����
                    address varchar2(50),--��ַ
                    phone varchar2(20),--�绰
                    mail  varchar2(20));--����
insert into press values(1001,'�����ʵ������','�����ж�����Ϧ���½�14��','010-22312345','rmyd@163.com');
insert into press values(1002,'����������','�����б�������·6��','010-298764563','bjcbc@163.com');
insert into press values(1003,'�㶫����������','������Խ�������ж�·472��','020-54329895',null);
insert into press values(1004,'�廪��ѧ������','�廪��ѧѧ�д���A��','010-87564839',null);
commit;
--���� author��
drop table authors;
create table authors(id int primary key,--���߱��
                     sname varchar2(20),--����
                     phone varchar2(20),--�绰
                     address varchar2(50)); --��ַ
insert into authors values(2001,'������','15678943456','������˳����');
insert into authors values(2002,'����','15834528764','�����������');
insert into authors values(2003,'����','13823146542','������������');
insert into authors values(2004,'������','13976342134','����ʡ��ɳ��');
insert into authors values(2005,'����','13298346542','�Ĵ�ʡ�ɶ���');
insert into authors values(2006,'·ң','13309234523','����ʡ������');
insert into authors values(2007,'���','','������ɣ��');
commit;
--������� booktype��
drop table booktype;
create table booktype(tid varchar2(20) primary key,--������
                      tname varchar2(20),--��������
                      loc varchar2(20));     --�ڷ�λ��
insert into booktype values('J001','�������','��¥1�Ź�');
insert into booktype values('Y001','ҽѧ��','��¥3�Ź�');
insert into booktype values('W001','��ѧ��','��¥5�Ź�');
insert into booktype values('L001','��ʷ��','��¥3�Ź�');
commit;
--�� book��
drop table book;
create table book(bno varchar2(20) primary key,--ͼ����
                  bname varchar2(50),--ͼ������
                  aid int,--����
                  pid int,--������
                  tid varchar2(20),--����
                  buy date,--��������
                  price number(7,2),--�۸�
                  buynum int); --���� 
insert into book values('J0001','���������',2001,1001,'J001',date '2016-1-5',12.5,5);
insert into book values('J0002','oracle�����ŵ���ͨ',2002,1004,'J001',date '2016-8-8',30,10);
insert into book values('Y0001','������������ҩ',2005,1003,'Y001',date '2016-2-4',20,20);
insert into book values('W0001','ƽ��������',2006,1003,'W001',date '2016-5-15',35,30);
insert into book values('W0002','��������',2007,1004,'W001',date '2016-4-9',31,22);
insert into book values('J0003','SQL����',2001,1004,'J001',date '2016-2-15',32,20);
insert into book values('J0004','SQL�����γ�',2002,1001,'J001',date '2016-6-6',28,10);
commit;           
--���ۼ�¼ sales��
drop table sales;
create table sales(orderid  int primary key,--������
                   bno varchar2(20),--ͼ����
                   selldate  date,--��������
                   sellnum   int);--��������
insert into sales values(10001,'J0001',date '2016-5-1',2);
insert into sales values(10002,'J0002',date '2016-9-6',1); 
insert into sales values(10003,'J0001',date '2016-2-5',1); 
insert into sales values(10004,'J0002',date '2016-10-1',1); 
insert into sales values(10005,'W0001',date '2016-6-18',5);
insert into sales values(10006,'J0003',date '2016-7-19',2);
commit; 
SELECT * FROM press; --������
SELECT * FROM authors; --����
SELECT * FROM booktype; --�������
SELECT * FROM book;  --��(����)
SELECT * FROM sales; --���ۼ�¼
(��������������ʾ����,��������ʾΪ0)
1.'�����ʵ������'��������Щ�飿��ʾ����,��������,������,�۸�
select b.bname,b.aid,p.pname,b.price
from book b join press p on b.pid=p.id
where p.pname='�����ʵ������'


2.'������'д����Щ�飿��ʾ����,����,����,������,�۸�
select b.bno,b.bname,a.sname,p.pname,b.price
from book b join authors a on b.aid=a.id join press p on b.pid=p.id
where a.sname='������'


3.ÿ�����������������˶����飿�����˶��ٱ������۶��Ƕ��٣�
  ͼ��ľ����Ƕ���?
  ��ʾ��������,�����������,�����������,������۶�,ͼ��ľ���
select p.pname ��������,count(1) �����������,sum(s.sellnum) �����������,sum(s.sellnum*b.price) ���۶�,avg(price) ͼ��ľ���
from book b  join sales s on b.bno = s.bno 
             join press p on b.pid=p.id
group by p.id,p.pname
  
4.ÿ���˸�д�˼����飿�����˶��ٱ������˶���Ǯ��ͼ��ľ����Ƕ���?
  ��ʾ������,�������,��������,���۽��,ͼ��ľ���
select a.sname ������,count(1) �������,sum(s.sellnum) ���۽��,sum(b.price*s.sellnum) ���۽��,avg(b.price) ͼ��ľ���
from book b join sales s on b.bno = s.bno join authors a on b.aid=a.id
group by b.aid,a.sname

  
5.ÿ������ÿ���¸������˶�������,���ٽ���ʾ����,�·�,����,���
select a.sname ����,to_char(s.selldate,'mm') �·�,sum(s.sellnum) ����,sum(b.price*s.sellnum) ���
from book b join sales s on b.bno = s.bno join authors a on b.aid=a.id
group by to_char(s.selldate,'mm'),a.sname

6.ÿ��������ÿ���¸������˶�������,���ٽ���ʾ�·�,��������,����,���
select to_char(s.selldate,'mm') �·�,p.pname ��������,sum(s.sellnum) ����,sum(s.sellnum*b.price) ���
from book b join sales s on b.bno = s.bno join press p on b.pid=p.id
group by to_char(s.selldate,'mm'),p.pname



7.ÿ��ͼ����������ٱ�����ʣ������������ʾ�������,������,ʣ����
select bt.tname �������,sum(b.buynum) ������,sum(b.buynum-s.sellnum) ʣ����
from book b join booktype bt on bt.tid=b.tid join sales s on b.bno=s.bno
group by bt.tid ,bt.tname


8.�������3������,һ����û����ȥ���飿��ʾ���������Ϣ
select *
  from book
 where bno not in (select distinct b.bno
                     from book b
                     join sales s
                       on b.bno = s.bno
                    where add_months(s.selldate, -3) < b.buy)
                    


9.��'�����ʵ������'�����'�������'�������д�'SQL'��ͼ��,
  ��ʾ����,�����,��������,ͼ��λ��
select b.bname,bt.tname,p.pname,bt.loc
from book b join press p on b.pid = p.id join booktype bt on b.tid = bt.tid
where p.pname = '�����ʵ������' and bt.tname = '�������' and b.bname like '%SQL%'


10.��дһ���洢����SP_PRESS��ʵ�����¹���
�ٽ����д��'���ҵ�����'�Ȿ��ļ۸�ĳ�56,pid�ĳ�'����������'��pid,
  tid�ĳɸĳ�'��¥3�Ź�'��tid
�ڽ��������,�廪��ѧ���������ļ۸�����10Ԫ
��ɾ�����ֽ�'������������ҩ'��۸�С��20����







