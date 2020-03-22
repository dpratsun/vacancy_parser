CREATE TABLE IF NOT EXISTS vacancy (
`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
`name` varchar(255) NOT NULL,
`text` text NOT NULL,
`url` varchar(255) NOT NULL);