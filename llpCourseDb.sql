USE [LLPCourse]
GO
/****** Object:  Table [dbo].[article]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[article](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[link] [nvarchar](max) NULL,
	[title] [nvarchar](max) NOT NULL,
	[content] [nvarchar](max) NOT NULL,
	[categoryId] [int] NOT NULL,
 CONSTRAINT [PK_article] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[imageLink] [nvarchar](max) NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[course]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[course](
	[id] [nvarchar](50) NOT NULL,
	[name] [nvarchar](200) NOT NULL,
	[description] [nvarchar](max) NOT NULL,
	[overview] [nvarchar](max) NOT NULL,
	[forWho] [nvarchar](max) NOT NULL,
	[requirement] [nvarchar](max) NOT NULL,
	[target] [nvarchar](max) NOT NULL,
	[imageLink] [nvarchar](max) NOT NULL,
	[rating] [float] NOT NULL,
	[ratingNum] [int] NOT NULL,
	[saleNum] [int] NOT NULL,
	[price] [float] NOT NULL,
	[duration] [int] NOT NULL,
	[createdAt] [datetime] NOT NULL,
	[updatedAt] [datetime] NULL,
	[createdBy] [int] NULL,
	[updatedBy] [int] NULL,
	[isProminent] [bit] NOT NULL,
	[status] [bit] NOT NULL,
	[levelId] [int] NOT NULL,
	[languageId] [int] NOT NULL,
 CONSTRAINT [PK_course] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[courseTag]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[courseTag](
	[courseId] [nvarchar](50) NOT NULL,
	[tagId] [int] NOT NULL,
 CONSTRAINT [PK_courseTag] PRIMARY KEY CLUSTERED 
(
	[courseId] ASC,
	[tagId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[courseTopic]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[courseTopic](
	[courseId] [nvarchar](50) NOT NULL,
	[topicId] [int] NOT NULL,
 CONSTRAINT [PK_courseTopic] PRIMARY KEY CLUSTERED 
(
	[courseId] ASC,
	[topicId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[discount]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[discount](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[discountValue] [float] NOT NULL,
	[startTime] [date] NOT NULL,
	[endTime] [date] NOT NULL,
	[courseId] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_discount] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[faqTopic]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[faqTopic](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[question] [nvarchar](max) NOT NULL,
	[answer] [nvarchar](max) NOT NULL,
	[topicId] [int] NOT NULL,
	[articleId] [int] NULL,
 CONSTRAINT [PK_faqTopic] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[language]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[language](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_language] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[lastViewCourse]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lastViewCourse](
	[courseId] [nvarchar](50) NOT NULL,
	[userId] [int] NOT NULL,
 CONSTRAINT [PK_lastViewCourse] PRIMARY KEY CLUSTERED 
(
	[courseId] ASC,
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[lecture]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lecture](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](max) NOT NULL,
	[goal] [nvarchar](max) NULL,
	[link] [nvarchar](max) NULL,
	[duration] [int] NULL,
	[createdAt] [datetime] NOT NULL,
	[updatedAt] [datetime] NULL,
	[createdBy] [int] NULL,
	[updatedBy] [int] NULL,
	[isFree] [bit] NOT NULL,
	[status] [bit] NOT NULL,
	[sectionId] [int] NOT NULL,
 CONSTRAINT [PK_lecture] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[level]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[level](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_level] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[prominentTopic]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[prominentTopic](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](200) NOT NULL,
	[overview] [nvarchar](max) NOT NULL,
	[topicId] [int] NOT NULL,
 CONSTRAINT [PK_prominentTopic] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[review]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[review](
	[courseId] [nvarchar](50) NOT NULL,
	[userId] [int] NOT NULL,
	[content] [nvarchar](1000) NOT NULL,
	[rating] [int] NOT NULL,
	[isProminent] [bit] NOT NULL,
 CONSTRAINT [PK_review] PRIMARY KEY CLUSTERED 
(
	[courseId] ASC,
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[section]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[section](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](max) NOT NULL,
	[courseId] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_section] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[subCategory]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[subCategory](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[status] [bit] NOT NULL,
	[categoryId] [int] NOT NULL,
 CONSTRAINT [PK_subCategory] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tag]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tag](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tag] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[topic]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[topic](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[allAbout] [nvarchar](max) NULL,
	[description] [nvarchar](max) NULL,
	[status] [bit] NOT NULL,
	[subCategoryId] [int] NOT NULL,
 CONSTRAINT [PK_topic] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[visitCourse]    Script Date: 1/15/2024 3:19:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[visitCourse](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[courseId] [nvarchar](50) NOT NULL,
	[userId] [int] NOT NULL,
	[visitAt] [datetime] NOT NULL,
 CONSTRAINT [PK_visitCourse] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[course] ADD  CONSTRAINT [DF_course_rating]  DEFAULT ((0)) FOR [rating]
GO
ALTER TABLE [dbo].[course] ADD  CONSTRAINT [DF_course_ratingNum]  DEFAULT ((0)) FOR [ratingNum]
GO
ALTER TABLE [dbo].[course] ADD  CONSTRAINT [DF_course_saleNum]  DEFAULT ((0)) FOR [saleNum]
GO
ALTER TABLE [dbo].[article]  WITH CHECK ADD  CONSTRAINT [FK_article_category] FOREIGN KEY([categoryId])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[article] CHECK CONSTRAINT [FK_article_category]
GO
ALTER TABLE [dbo].[course]  WITH CHECK ADD  CONSTRAINT [FK_course_language] FOREIGN KEY([languageId])
REFERENCES [dbo].[language] ([id])
GO
ALTER TABLE [dbo].[course] CHECK CONSTRAINT [FK_course_language]
GO
ALTER TABLE [dbo].[course]  WITH CHECK ADD  CONSTRAINT [FK_course_level] FOREIGN KEY([levelId])
REFERENCES [dbo].[level] ([id])
GO
ALTER TABLE [dbo].[course] CHECK CONSTRAINT [FK_course_level]
GO
ALTER TABLE [dbo].[courseTag]  WITH CHECK ADD  CONSTRAINT [FK_courseTag_course] FOREIGN KEY([courseId])
REFERENCES [dbo].[course] ([id])
GO
ALTER TABLE [dbo].[courseTag] CHECK CONSTRAINT [FK_courseTag_course]
GO
ALTER TABLE [dbo].[courseTag]  WITH CHECK ADD  CONSTRAINT [FK_courseTag_tag] FOREIGN KEY([tagId])
REFERENCES [dbo].[tag] ([id])
GO
ALTER TABLE [dbo].[courseTag] CHECK CONSTRAINT [FK_courseTag_tag]
GO
ALTER TABLE [dbo].[courseTopic]  WITH CHECK ADD  CONSTRAINT [FK_courseTopic_course] FOREIGN KEY([courseId])
REFERENCES [dbo].[course] ([id])
GO
ALTER TABLE [dbo].[courseTopic] CHECK CONSTRAINT [FK_courseTopic_course]
GO
ALTER TABLE [dbo].[courseTopic]  WITH CHECK ADD  CONSTRAINT [FK_courseTopic_topic] FOREIGN KEY([topicId])
REFERENCES [dbo].[topic] ([id])
GO
ALTER TABLE [dbo].[courseTopic] CHECK CONSTRAINT [FK_courseTopic_topic]
GO
ALTER TABLE [dbo].[discount]  WITH CHECK ADD  CONSTRAINT [FK_discount_course] FOREIGN KEY([courseId])
REFERENCES [dbo].[course] ([id])
GO
ALTER TABLE [dbo].[discount] CHECK CONSTRAINT [FK_discount_course]
GO
ALTER TABLE [dbo].[faqTopic]  WITH CHECK ADD  CONSTRAINT [FK_faqTopic_article] FOREIGN KEY([articleId])
REFERENCES [dbo].[article] ([id])
GO
ALTER TABLE [dbo].[faqTopic] CHECK CONSTRAINT [FK_faqTopic_article]
GO
ALTER TABLE [dbo].[faqTopic]  WITH CHECK ADD  CONSTRAINT [FK_faqTopic_topic] FOREIGN KEY([topicId])
REFERENCES [dbo].[topic] ([id])
GO
ALTER TABLE [dbo].[faqTopic] CHECK CONSTRAINT [FK_faqTopic_topic]
GO
ALTER TABLE [dbo].[lastViewCourse]  WITH CHECK ADD  CONSTRAINT [FK_lastViewCourse_course] FOREIGN KEY([courseId])
REFERENCES [dbo].[course] ([id])
GO
ALTER TABLE [dbo].[lastViewCourse] CHECK CONSTRAINT [FK_lastViewCourse_course]
GO
ALTER TABLE [dbo].[lecture]  WITH CHECK ADD  CONSTRAINT [FK_lecture_section] FOREIGN KEY([sectionId])
REFERENCES [dbo].[section] ([id])
GO
ALTER TABLE [dbo].[lecture] CHECK CONSTRAINT [FK_lecture_section]
GO
ALTER TABLE [dbo].[prominentTopic]  WITH CHECK ADD  CONSTRAINT [FK_prominentTopic_topic] FOREIGN KEY([topicId])
REFERENCES [dbo].[topic] ([id])
GO
ALTER TABLE [dbo].[prominentTopic] CHECK CONSTRAINT [FK_prominentTopic_topic]
GO
ALTER TABLE [dbo].[review]  WITH CHECK ADD  CONSTRAINT [FK_review_course] FOREIGN KEY([courseId])
REFERENCES [dbo].[course] ([id])
GO
ALTER TABLE [dbo].[review] CHECK CONSTRAINT [FK_review_course]
GO
ALTER TABLE [dbo].[section]  WITH CHECK ADD  CONSTRAINT [FK_section_course] FOREIGN KEY([courseId])
REFERENCES [dbo].[course] ([id])
GO
ALTER TABLE [dbo].[section] CHECK CONSTRAINT [FK_section_course]
GO
ALTER TABLE [dbo].[subCategory]  WITH CHECK ADD  CONSTRAINT [FK_subCategory_category] FOREIGN KEY([categoryId])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[subCategory] CHECK CONSTRAINT [FK_subCategory_category]
GO
ALTER TABLE [dbo].[topic]  WITH CHECK ADD  CONSTRAINT [FK_topic_subCategory] FOREIGN KEY([subCategoryId])
REFERENCES [dbo].[subCategory] ([id])
GO
ALTER TABLE [dbo].[topic] CHECK CONSTRAINT [FK_topic_subCategory]
GO
ALTER TABLE [dbo].[visitCourse]  WITH CHECK ADD  CONSTRAINT [FK_visitCourse_course] FOREIGN KEY([courseId])
REFERENCES [dbo].[course] ([id])
GO
ALTER TABLE [dbo].[visitCourse] CHECK CONSTRAINT [FK_visitCourse_course]
GO
