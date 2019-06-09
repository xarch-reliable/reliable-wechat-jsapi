VERSION=0.0.1
NAME=reliable-wechat-pay-id-manager
PORT=8230
FILE=/var/spring-project/reliable8

all:config restart

create:config docker run

config:
	mkdir -p $(FILE)/$(NAME)
	cp -i ../target/$(NAME)-$(VERSION).jar $(FILE)/$(NAME)/app.jar

docker:
	docker build -t reliable8/$(NAME):$(VERSION) .

run:
	docker run --name $(NAME) \
	--restart=always \
	-v $(FILE)/$(NAME)/app.jar:/app.jar \
	-v $(FILE)/logs/$(NAME):/reliable-logs \
	-p $(PORT):$(PORT) \
	-d reliable8/$(NAME):$(VERSION)

restart:
	docker restart $(NAME)

remove:
	docker stop $(NAME) && docker rm $(NAME)
	docker rmi reliable8/$(NAME):$(VERSION)