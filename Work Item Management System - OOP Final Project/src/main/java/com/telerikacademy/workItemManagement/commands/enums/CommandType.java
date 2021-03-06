package com.telerikacademy.workItemManagement.commands.enums;

public enum CommandType {
  SHOWCOMMANDS,
  CREATENEWPERSON,
  SHOWALLPEOPLE,
  SHOWPERSONSACTIVITY,
  CREATENEWTEAM,
  SHOWALLTEAMS,
  SHOWTEAMSACTIVITY,
  ADDPERSONTOTEAM,
  SHOWALLTEAMMEMBERS,
  CREATENEWBOARDINTEAM,
  SHOWALLTEAMBOARDS,
  SHOWBOARDSACTIVITY,
  CREATENEWBUGINBOARD,
  CREATENEWSTORYINBOARD,
  CREATENEWFEEDBACKINBOARD,
  ADDCOMMENTWORKITEM,
  CHANGEBUGPRIORITY,
  CHANGEBUGSEVERITY,
  CHANGEBUGSTATUS,
  CHANGESTORYPRIORITY,
  CHANGESTORYSIZE,
  CHANGESTORYSTATUS,
  CHANGEFEEDBACKRATING,
  CHANGEFEEDBACKSTATUS,
  ASSIGNWORKITEMTOPERSON,
  UNASSIGNWORKITEMTOPERSON,
  ADDCOMMENTTOWORKITEM,
  LISTALLWORKITEMS,
  LISTALLWORKITEMSFILTEREDBYBUG,
  LISTALLWORKITEMSFILTEREDBYSTORY,
  LISTALLWORKITEMSFILTEREDBYFEEDBACK,
  LISTALLWORKITEMSFILTEREDBYSTATUS,
  LISTALLWORKITEMSFILTEREDBYASSIGNEE,
  LISTALLWORKITEMSFILTEREDBYSTATUSANDASSIGNEE,
  LISTALLWORKITEMSSORTEDBYTITLE,
  LISTALLWORKITEMSSORTEDBYPRIORITY,
  LISTALLWORKITEMSSORTEDBYSEVERITY,
  LISTALLWORKITEMSSORTEDBYSIZE,
  LISTALLWORKITEMSSORTEDBYRATING;
}
