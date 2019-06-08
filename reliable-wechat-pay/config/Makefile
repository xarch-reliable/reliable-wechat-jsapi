VERSION=0.0.1
NAME=reliable-wechat-pay
PORT=8210
FILE=/var/spring-project/reliable8
CERTNAME=apiclient_cert.p12
CERTPATH=/etc/ssl/wechatcert

all:config restart

create:config docker run

config:
	mkdir -p $(FILE)/$(NAME)
	cp -i ../target/$(NAME)-$(VERSION).jar $(FILE)/$(NAME)/app.jar
	cp -i $(CERTPATH)/$(CERTNAME) $(FILE)/$(CERTNAME)

docker:
	docker build -t lnnupet/$(NAME):$(VERSION) .

run:
	docker run --name $(NAME) \
	--restart=always \
	-v $(FILE)/$(NAME)/app.jar:/app.jar \
	-v $(FILE)/$(CERTNAME):/etc/ssl/pay/$(CERTNAME) \
	-v $(FILE)/logs/$(NAME):/reliable-logs \
	-p $(PORT):$(PORT) \
	-d lnnupet/$(NAME):$(VERSION)

restart:
	docker restart $(NAME)

remove:
	docker stop $(NAME) && docker rm $(NAME)
	docker rmi lnnupet/$(NAME):$(VERSION)