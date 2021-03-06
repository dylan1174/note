#!/usr/bin/python3
# coding: utf-8

import queue
import simplejson
import threading
import subprocess
import requests
import warnings
import sys
warnings.filterwarnings(action='ignore')

urls_queue = queue.Queue()
tclose=0

def opt2File(paths):
	try:
		f = open('crawl_result.txt','a')
		f.write(paths + '\n')
	finally:
		f.close()

def opt2File2(subdomains):
	try:
		f = open('sub_domains.txt','a')
		f.write(subdomains + '\n')
	finally:
		f.close()



def request0():
	while tclose==0 or urls_queue.empty() == False:
		if(urls_queue.qsize()==0):
			continue
		sys.stdout.write('\r队列中剩余'+str(urls_queue.qsize()) + '个url')
		req =urls_queue.get()
		proxies = {
		'http': 'http://127.0.0.1:7777',
		'https': 'http://127.0.0.1:7777',
		}
		urls0 =req['url']
		headers0 =req['headers']
		method0=req['method']
		data0=req['data']
		try:
			if(method0=='GET'):
				a = requests.get(urls0, headers=headers0, proxies=proxies,timeout=30,verify=False)
				opt2File(urls0)
			elif(method0=='POST'):
				a = requests.post(urls0, headers=headers0,data=data0, proxies=proxies,timeout=30,verify=False)
				opt2File(urls0)
		except:
			continue
	return

def main(data1):
	print('进入主函数')
	target = data1
	print('正在爬取' + data1)
	headers = {
        "Cookie":"GF_OAUTH2_SSO=a4270746-c908-4955-9460-1f0b62281fec; GF_OAUTH2_LOGINTYPE=portal"
    }
	cmd = ["crawlergo", "-c", "D:\\tool\\chromium\\chrome.exe","-t", "15","-f","smart","--fuzz-path","--custom-headers",simplejson.dumps(headers),"--output-mode", "json", target]
	rsp = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
	output, error = rsp.communicate()
	try:
		result = simplejson.loads(output.decode().split("--[Mission Complete]--")[1])
	except:
		print("爬取错误")
		print(error)
		return
	req_list = result["req_list"]
	sub_domain = result["sub_domain_list"]
	print(data1)
	print("[crawl ok]")
	try:
		for subd in sub_domain:
			opt2File2(subd)
	except:
		pass
	try:
		for req in req_list:
			urls_queue.put(req)
	except:
		return
	print("[scanning]")



if __name__ == '__main__':
	file = open("targets.txt")
	t = threading.Thread(target=request0)
	t.start()
	for text in file.readlines():
		data1 = text.strip('\n')
		print('从文件得到' + data1)
		main(data1)
	tclose=1
