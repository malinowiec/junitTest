Given a messenger
When set arguments serverToSend to inf.ug.edu.pl and messageToSend to somemessage
Then sendValidS should return 0

When set arguments serverToSend to inf.ug.edu.pl and messageToSend to ab
Then sendValidS should return 2

When set arguments serverToSend to inf.ug.edu.eu and messageToSend to somemessage
Then sendValidS should return 1

When set arguments serverToSend to inf.ug.edu.eu and messageToSend to ab
Then sendValidS should return 2



