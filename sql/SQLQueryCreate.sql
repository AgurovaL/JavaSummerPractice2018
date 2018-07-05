CREATE TABLE [User] (
	ID_user integer IDENTITY (1,1) NOT NULL,
	Name varchar(50) NOT NULL,
	Login varchar(50) NOT NULL,
	Password varchar(50) NOT NULL,
  CONSTRAINT [PK_USER] PRIMARY KEY CLUSTERED
  (
  [ID_user] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)
)
GO

CREATE TABLE [Image] (
	ID_image integer IDENTITY (1,1) NOT NULL,
	Name varchar(50) NOT NULL,
	Adress varchar(50) NOT NULL,
  CONSTRAINT [PK_Image] PRIMARY KEY CLUSTERED
  (
  [ID_image] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)
)
GO

CREATE TABLE [Favorite_image] (
	ID_favorite_image integer IDENTITY (1,1) NOT NULL,
	ID_user integer NOT NULL,
	ID_image integer NOT NULL,
  CONSTRAINT [PK_FAVORITE_IMAGE] PRIMARY KEY CLUSTERED
  (
  [ID_favorite_image] ASC
  ) WITH (IGNORE_DUP_KEY = OFF)

)
GO