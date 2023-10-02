/**
 * 284. Peeking Iterator
 * 这道题我想的方法是另起一个iterator先跑一步
 * 那答案用的是差不多的方法 就是peek的时候直接跑一步next 但是把value暂存起来
 * 那之后call多少次peek都是return这个存好的值 call next那也是return这个 但是要invalidate掉
 * 这样之后不管是next还是peek都要新的next
 * 思路不难 实现起来是非常巧妙的 核心就是peek直接跑一步然后暂存起来
*/
/*
 * Below is the interface for Iterator, which is already defined for you.
 * **DO NOT** modify the interface for Iterator.
 *
 *  class Iterator {
 *		struct Data;
 * 		Data* data;
 *  public:
 *		Iterator(const vector<int>& nums);
 * 		Iterator(const Iterator& iter);
 *
 * 		// Returns the next element in the iteration.
 *		int next();
 *
 *		// Returns true if the iteration has more elements.
 *		bool hasNext() const;
 *	};
 */

class PeekingIterator : public Iterator {
private:
    bool valid;
    int cached_value;
public:
	PeekingIterator(const vector<int>& nums) : Iterator(nums) {
	    // Initialize any member here.
	    // **DO NOT** save a copy of nums and manipulate it directly.
	    // You should only use the Iterator interface methods.
	    valid = false;
	}
	
    // Returns the next element in the iteration without advancing the iterator.
	int peek() {
        if(!valid) { // this peek not runned before a next
            valid = true;
            cached_value = Iterator::next();
        }
        return cached_value;
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	int next() {
	    if(!valid) return Iterator::next();
        valid = false; // cache not valid, next need to be update
        return cached_value;
	}
	
	bool hasNext() const {
	    if(valid) return true;
        return Iterator::hasNext();
	}
};