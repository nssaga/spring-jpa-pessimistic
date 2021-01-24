-- Create ARTICLE table
CREATE TABLE article (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  comment_count bigint(20) DEFAULT NULL,
  title varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Create comment table
CREATE TABLE comment (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  article_id bigint(20) DEFAULT NULL,
  content varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
