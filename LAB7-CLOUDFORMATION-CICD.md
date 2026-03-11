# DevOps Lab 7: CloudFormation + CI/CD Pipeline Deployment

## Objective
This lab demonstrates an Infrastructure-as-Code workflow where every push triggers an automated deployment.

### Students will learn to:
1. Store a CloudFormation template in a Git repository.
2. Create a CI/CD pipeline using AWS services.
3. Automatically deploy infrastructure with CloudFormation.
4. Understand a real DevOps IaC workflow.

---

## Architecture Flow
Developer push ➜ Git repository ➜ AWS CodePipeline ➜ AWS CodeBuild ➜ AWS CloudFormation ➜ EC2 infrastructure

Whenever a developer pushes code, the pipeline automatically deploys infrastructure.

---

## Infrastructure Provisioned by CloudFormation
- Security Group (SSH + HTTP)
- EC2 instance
- Apache web server with a custom index page

---

## Prerequisites
- AWS account
- GitHub repository (or CodeCommit)
- IAM permissions for:
  - CodePipeline
  - CodeBuild
  - CloudFormation
  - EC2

---

## Repository Files
This lab adds the following files:
- `template.yaml` – CloudFormation stack template
- `buildspec.yml` – CodeBuild instructions to deploy stack

---

## Setup Steps

### 1) Create repository
Create repository: `devops-cfn-pipeline`.

### 2) Add CloudFormation template
Add `template.yaml` (included in this repo).
Commit and push.

### 3) Add build specification
Add `buildspec.yml` (included in this repo).
Commit and push.

### 4) Create S3 artifacts bucket
Create an S3 bucket, for example:
- `devops-cfn-pipeline-artifacts`

### 5) Create CodeBuild project
- Project name: `cfn-build-project`
- Source: GitHub repository
- Environment: Managed image, Amazon Linux, standard runtime
- Privileged mode: OFF
- Buildspec: `buildspec.yml`

### 6) Create CodePipeline
- Pipeline name: `devops-cfn-pipeline`
- Source stage: GitHub repo + `main`
- Build stage: CodeBuild project `cfn-build-project`
- Deploy stage: CloudFormation stack `devops-cfn-stack`, template `template.yaml`

### 7) Run pipeline
Pipeline stages:
- Source
- Build
- Deploy

### 8) Verify infrastructure
Check EC2 console for new instance.

### 9) Test web server
Open:
- `http://<EC2-Public-IP>`

Expected output:
- `Hello from DevOps CloudFormation Pipeline`

---

## Automation Test (Change-Driven Deployment)
Modify user data message in `template.yaml` (for example, `Version 2 Deployment`), then commit and push.

Pipeline behavior:
1. Detects source change
2. Executes CodeBuild
3. Updates CloudFormation stack
4. Deploys updated infrastructure

---

## Troubleshooting
- Pipeline failed: check CodePipeline execution details
- Build failed: check CloudWatch Logs for CodeBuild
- CloudFormation failed: check Stack Events

---

## Interview Review
- **What is Infrastructure as Code?** Managing infrastructure using code templates.
- **Why CloudFormation with CI/CD?** To deploy infrastructure automatically on code changes.
- **What does `buildspec.yml` do?** Defines commands CodeBuild executes.
- **What happens when template changes?** Pipeline updates the CloudFormation stack automatically.
