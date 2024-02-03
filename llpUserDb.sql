USE [LLPUser]
GO
/****** Object:  Table [dbo].[message]    Script Date: 1/15/2024 3:22:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[message](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[message] [nvarchar](max) NOT NULL,
	[createdAt] [datetime] NOT NULL,
	[userId] [int] NOT NULL,
	[createdBy] [int] NOT NULL,
 CONSTRAINT [PK_message] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[notification]    Script Date: 1/15/2024 3:22:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[notification](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[message] [nvarchar](max) NOT NULL,
	[createdAt] [datetime] NOT NULL,
	[createdBy] [int] NOT NULL,
 CONSTRAINT [PK_notification] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[registerInstructorAnswer]    Script Date: 1/15/2024 3:22:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[registerInstructorAnswer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[answer] [nvarchar](max) NOT NULL,
	[questionId] [int] NOT NULL,
 CONSTRAINT [PK_registerInstructorAnswer] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[registerInstructorForm]    Script Date: 1/15/2024 3:22:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[registerInstructorForm](
	[userId] [int] NOT NULL,
	[questionId] [int] NOT NULL,
	[answerId] [int] NOT NULL,
 CONSTRAINT [PK_registerInstructorForm] PRIMARY KEY CLUSTERED 
(
	[userId] ASC,
	[questionId] ASC,
	[answerId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[registerInstructorQuestion]    Script Date: 1/15/2024 3:22:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[registerInstructorQuestion](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](max) NOT NULL,
	[description] [nvarchar](max) NOT NULL,
	[question] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_registerInstructorQuestion] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[role]    Script Date: 1/15/2024 3:22:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_role] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user]    Script Date: 1/15/2024 3:22:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fullname] [nvarchar](200) NOT NULL,
	[email] [nvarchar](max) NOT NULL,
	[password] [nvarchar](max) NOT NULL,
	[headline] [nvarchar](100) NULL,
	[biography] [nvarchar](max) NULL,
	[twitterLink] [nvarchar](max) NULL,
	[facebookLink] [nvarchar](max) NULL,
	[linkedinLink] [nvarchar](max) NULL,
	[youtubeLink] [nvarchar](max) NULL,
	[imageLink] [nvarchar](max) NULL,
	[otp] [int] NULL,
	[verified] [bit] NOT NULL,
	[participateDay] [date] NOT NULL,
 CONSTRAINT [PK_user] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[userNotification]    Script Date: 1/15/2024 3:22:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[userNotification](
	[userId] [int] NOT NULL,
	[notificationId] [int] NOT NULL,
 CONSTRAINT [PK_userNotification] PRIMARY KEY CLUSTERED 
(
	[userId] ASC,
	[notificationId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[userRole]    Script Date: 1/15/2024 3:22:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[userRole](
	[userId] [int] NOT NULL,
	[roleId] [int] NOT NULL,
 CONSTRAINT [PK_userRole] PRIMARY KEY CLUSTERED 
(
	[userId] ASC,
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[wishlistAndCart]    Script Date: 1/15/2024 3:22:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[wishlistAndCart](
	[courseId] [nvarchar](50) NOT NULL,
	[userId] [int] NOT NULL,
	[defaultPrice] [float] NOT NULL,
	[price] [float] NOT NULL,
	[isWishlist] [bit] NOT NULL,
	[isCart] [bit] NOT NULL,
 CONSTRAINT [PK_wishlistAndCart] PRIMARY KEY CLUSTERED 
(
	[courseId] ASC,
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[yourCourse]    Script Date: 1/15/2024 3:22:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[yourCourse](
	[courseId] [nvarchar](50) NOT NULL,
	[userId] [int] NOT NULL,
	[orderId] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_yourCourse] PRIMARY KEY CLUSTERED 
(
	[courseId] ASC,
	[userId] ASC,
	[orderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[yourLecture]    Script Date: 1/15/2024 3:22:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[yourLecture](
	[userId] [int] NOT NULL,
	[courseId] [nvarchar](50) NOT NULL,
	[lectureId] [int] NOT NULL,
 CONSTRAINT [PK_yourLecture] PRIMARY KEY CLUSTERED 
(
	[userId] ASC,
	[courseId] ASC,
	[lectureId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[message]  WITH CHECK ADD  CONSTRAINT [FK_message_user] FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[message] CHECK CONSTRAINT [FK_message_user]
GO
ALTER TABLE [dbo].[message]  WITH CHECK ADD  CONSTRAINT [FK_message_user1] FOREIGN KEY([createdBy])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[message] CHECK CONSTRAINT [FK_message_user1]
GO
ALTER TABLE [dbo].[registerInstructorAnswer]  WITH CHECK ADD  CONSTRAINT [FK_registerInstructorAnswer_registerInstructorQuestion] FOREIGN KEY([questionId])
REFERENCES [dbo].[registerInstructorQuestion] ([id])
GO
ALTER TABLE [dbo].[registerInstructorAnswer] CHECK CONSTRAINT [FK_registerInstructorAnswer_registerInstructorQuestion]
GO
ALTER TABLE [dbo].[registerInstructorForm]  WITH CHECK ADD  CONSTRAINT [FK_registerInstructorForm_registerInstructorAnswer] FOREIGN KEY([answerId])
REFERENCES [dbo].[registerInstructorAnswer] ([id])
GO
ALTER TABLE [dbo].[registerInstructorForm] CHECK CONSTRAINT [FK_registerInstructorForm_registerInstructorAnswer]
GO
ALTER TABLE [dbo].[registerInstructorForm]  WITH CHECK ADD  CONSTRAINT [FK_registerInstructorForm_registerInstructorQuestion] FOREIGN KEY([questionId])
REFERENCES [dbo].[registerInstructorQuestion] ([id])
GO
ALTER TABLE [dbo].[registerInstructorForm] CHECK CONSTRAINT [FK_registerInstructorForm_registerInstructorQuestion]
GO
ALTER TABLE [dbo].[registerInstructorForm]  WITH CHECK ADD  CONSTRAINT [FK_registerInstructorForm_user] FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[registerInstructorForm] CHECK CONSTRAINT [FK_registerInstructorForm_user]
GO
ALTER TABLE [dbo].[userNotification]  WITH CHECK ADD  CONSTRAINT [FK_userNotification_notification] FOREIGN KEY([notificationId])
REFERENCES [dbo].[notification] ([id])
GO
ALTER TABLE [dbo].[userNotification] CHECK CONSTRAINT [FK_userNotification_notification]
GO
ALTER TABLE [dbo].[userNotification]  WITH CHECK ADD  CONSTRAINT [FK_userNotification_user] FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[userNotification] CHECK CONSTRAINT [FK_userNotification_user]
GO
ALTER TABLE [dbo].[userRole]  WITH CHECK ADD  CONSTRAINT [FK_userRole_role] FOREIGN KEY([roleId])
REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[userRole] CHECK CONSTRAINT [FK_userRole_role]
GO
ALTER TABLE [dbo].[userRole]  WITH CHECK ADD  CONSTRAINT [FK_userRole_user] FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[userRole] CHECK CONSTRAINT [FK_userRole_user]
GO
ALTER TABLE [dbo].[wishlistAndCart]  WITH CHECK ADD  CONSTRAINT [FK_wishlistAndCart_user] FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[wishlistAndCart] CHECK CONSTRAINT [FK_wishlistAndCart_user]
GO
ALTER TABLE [dbo].[yourCourse]  WITH CHECK ADD  CONSTRAINT [FK_yourCourse_user] FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[yourCourse] CHECK CONSTRAINT [FK_yourCourse_user]
GO
ALTER TABLE [dbo].[yourLecture]  WITH CHECK ADD  CONSTRAINT [FK_yourLecture_user] FOREIGN KEY([userId])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[yourLecture] CHECK CONSTRAINT [FK_yourLecture_user]
GO
