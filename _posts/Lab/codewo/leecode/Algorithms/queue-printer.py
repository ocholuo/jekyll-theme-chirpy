






# On any average day 
# about 10 students are working in the lab at any given hour. 
# These students typically print up to twice during that time
# the length of these tasks ranges from 1 to 20 pages. 
# draft quality.  10 pages/minute
# better quality, 5 pages/minute.

# As students submit printing tasks, add them to a waiting list, a queue of print tasks attached to the printer. 
# When the printer completes a task, it will look at the queue to see if there are any remaining tasks to process. 
# the average amount of time students will wait equal to the average amount of time a task waits in the queue.


# 10 students x 2 pages per hours
# 20/60min  
# 1page/180sec
# For every second simulate the chance that a print task occurs by generating a random number between 1 and 180 inclusive. 
# If the number is 180, we say a task has been created. Note that it is possible that many tasks could be created in a row or we may wait quite a while for a task to appear. That is the nature of simulation. You want to simulate the real situation as closely as possible given that you know general parameters.


import random

class Queue:
    """Queue implementation as a list"""

    def __init__(self):
        """Create new queue"""
        self._items = []

    def is_empty(self):
        """Check if the queue is empty"""
        return not bool(self._items)

    def enqueue(self, item):
        """Add an item to the queue"""
        self._items.insert(0, item)

    def dequeue(self):
        """Remove an item from the queue"""
        return self._items.pop()

    def size(self):
        """Get the number of items in the queue"""
        return len(self._items)


class Printer:
    def __init__(self, ppm):      # self define
        self.pagerate = ppm       # speed  pages/minute
        self.currentTask = None   # paint mission
        self.timeRemaining = 0    # current mission remain print time

    def busy(self):   # if busy?
        return self.current_task is not None

    # decrements the internal timer and sets the printer to idle if the task is completed.
    def tick(self):
        if self.currentTask != None:
            self.timeRemaining = self.timeRemaining -1
            if self.timeRemaining <=0:
                self.currentTask = None

    def startNext(self, newtask):
        self.currentTask = newtask
        self.timeRemaining = newtask.getpages() * 60 / self.pagerate
        # (pages/speed 10pages/min )*60s


class Task:
    def __init__(self, time):
        self.timestamp = time                 # time created
        self.pages = random.randrange(1,21)   # pages

    def getStamp(self):
        return self.timestamp

    def getPages(self):
        return self.pages       

    def waitTime(self, currenttime):          # call to see how long time it has wait
         return currenttime - self.timestamp


# whether if a new task creates
def newPrintTask():    
    num = random.randrange(1,181)    # 20task10student/hour -> 1/180s
    return num == 181


def simulation(numSeconds, pagesPerMinute):
    labprinter = Printer(pagesPerMinute)
    printQueue = Queue()
    waitingtimes = []

    for currentSecond in range(numSeconds):  # time period, add mission
        if newPrintTask():   
            task = Task(currentSecond)
            printQueue.enqueue(task)

        if (not labprinter.busy()) and (not printQueue.is_empty()):
            nexttask = printQueue.dequeue()  # take the task out
            waitingtimes.append(nexttask.waitTime(currentSecond))
            labprinter.startNext(nexttask)
            
        labprinter.tick()  # keep working

    averageWait = sum(waitingtimes) / len(waitingtimes)
    print("Average wait %f secs %d tasks remaining." %(averageWait, printQueue.size()))


for i in range(10):
    simulation(3600,5)    # 5ppm, 1 hour, run 10 times
    simulation(3600,10)   # 10ppm, 1 hour, run 10 times
