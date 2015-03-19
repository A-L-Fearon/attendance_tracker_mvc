# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table member (
  id                        varchar(255) not null,
  fname                     varchar(255),
  lname                     varchar(255),
  email                     varchar(255),
  constraint pk_member primary key (id))
;

create table records (
  date                      timestamp not null,
  attended                  boolean,
  dues                      integer,
  member_id                 varchar(255),
  constraint pk_records primary key (date))
;

create sequence member_seq;

create sequence records_seq;

alter table records add constraint fk_records_member_1 foreign key (member_id) references member (id) on delete restrict on update restrict;
create index ix_records_member_1 on records (member_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists member;

drop table if exists records;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists member_seq;

drop sequence if exists records_seq;

