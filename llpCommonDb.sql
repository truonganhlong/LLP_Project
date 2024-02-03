USE [LLPCommon]
GO
/****** Object:  Table [dbo].[intro]    Script Date: 1/15/2024 3:21:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[intro](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](100) NOT NULL,
	[content] [nvarchar](500) NOT NULL,
	[imageLink] [nvarchar](max) NOT NULL,
	[status] [bit] NOT NULL,
	[introMapId] [int] NOT NULL,
 CONSTRAINT [PK_intro] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[introMap]    Script Date: 1/15/2024 3:21:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[introMap](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[map] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_introMap] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[intro]  WITH CHECK ADD  CONSTRAINT [FK_intro_introMap] FOREIGN KEY([introMapId])
REFERENCES [dbo].[introMap] ([id])
GO
ALTER TABLE [dbo].[intro] CHECK CONSTRAINT [FK_intro_introMap]
GO
