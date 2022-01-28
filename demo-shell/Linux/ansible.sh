sshpass -p Admin123 ansible 10.20.0.1 -i 10.20.0.1, -m shell -a 'echo aaa>>aa'

sshpass -p Admin123 ansible 10.20.0.1 -i 10.20.0.1,  -m authorized_key -a user=root key='{{ lookup('file', '/root/.ssh/id_rsa.pub')}}'